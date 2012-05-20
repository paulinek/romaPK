package net.panda2.roma.game;

import net.panda2.RingInteger;
import net.panda2.RingInteger0;
import net.panda2.game.card.Tableau;
import net.panda2.game.dice.DiceCollection;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaCheatingException;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */

public class RomaGameState {
    int numPlayers;
    PlayerState player[];
    RingInteger playerNo;
    StashFactory<VPStash> vpStash;
    StashFactory<MoneyStash> moneyStash;
    VPStash tabletopVPStockpile;
    ViewableCardDeck maindeck, discard;
    GameEngine ge;
    RomaRules ruleset;
    boolean gameOver;

    Tableau<PJRomaCard> diceDiscs;
    DiceCollection battleDice;
    MoneyStash treasury
           ;

    public PJRomaCard dealRandomCard(AuthToken tk) {
        if(ge.authenticateToken(tk)) {
            maindeck.shuffleDeck();
            PJRomaCard c = maindeck.dealCard();
            return c;
        }
        return null;
    }

    public    void reshuffleDeck(AuthToken tk) {
        if(ge.authenticateToken(tk)) {
            maindeck.shuffleDeck();
        }
    }

    public    PlayerState currentPlayer(AuthToken tk) {
    return        ge.authenticatedReturn(tk, currentPlayer());
     }

    PlayerState currentPlayer() {
        return player[playerNo.get()];
    }
    public PlayerState getNextPlayer(AuthToken tk) {
        return ge.authenticatedReturn(tk, getNextPlayer());
    }

    PlayerState getNextPlayer () {
        return player[playerNo.next()];

    }

    private RomaGameState(RomaRules ruleSet, GameEngine ge) throws RomaException {
        //ref rules for numPlayers
        numPlayers=ruleSet.numPlayers;
        this.ge = ge;
        this.ruleset = ruleset;
        // player is an array of size numPlayers
        player=new PlayerState[numPlayers];

        // RingInteger expects the maximum valid number, so 0..numPlayers-1
        playerNo =new RingInteger0(0,numPlayers);
        // create a new net.panda2.roma.game.PlayerState object for each of numPlayers
        int i;

        // create stashes (so that piles can be created later
        vpStash = new StashFactory<VPStash>(ruleSet.gameTotalVP, VPStash.createStash(0,0));
        moneyStash = new StashFactory<MoneyStash>(Integer.MAX_VALUE, MoneyStash.createStash(0,0));
        for (i=0; i<numPlayers; i++){
            player[i]=new PlayerState(ruleSet, ge,"Player"+i, vpStash,moneyStash);
        }
        // seed stockpile with init VP
        tabletopVPStockpile=vpStash.make(ruleSet.tableInitVP, ruleSet.minVP);
        treasury = moneyStash.make(Integer.MAX_VALUE-65536);

        if (sanityCheckInitVPTotal(ruleSet)!=true){
            throw new RomaException("Sanity Check Failed: VP init doesn't add up");
        }
        maindeck = new ViewableCardDeck();
        discard = new ViewableCardDeck();
        FredCardFactory.createInitialCards(maindeck);
        battleDice = new DiceCollection();  // TODO - parameterise this
        //
        }

    public Stash getVP(AuthToken tk) throws RomaException {
        ge.authenticateOrDie(tk);
        return tabletopVPStockpile;
    }


    private boolean sanityCheckInitVPTotal(RomaRules ruleSet) throws RomaCheatingException {
        // VP post-init sanity check
        // get amount from each player
        // get amount from tabletop stockpile
        // add them all up, and game total should be rules.gameTotalVP

        if(vpStash.checkForCheaters()) {
            return false;
        }
        boolean saneQ=true;

        int gameTotalVPSoFar=0;
        int j;
        for (j=0; j<player.length; j++){
            gameTotalVPSoFar+=player[j].vp.getAmount();
        }
        gameTotalVPSoFar+=tabletopVPStockpile.getAmount();

        if (gameTotalVPSoFar!=ruleSet.gameTotalVP){
            saneQ=false;
        }
        return saneQ;
    }

    public static RomaGameState createGameState(RomaRules rules, GameEngine gameEngine) throws RomaException {
        return new RomaGameState(rules, gameEngine);
    }

    public void setPlayerNo(int player, AuthToken tk) {
        try {
            ge.authenticateOrDie(tk);
        } catch (RomaException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        playerNo.set(player);}

     void nextPlayerTurn() {
        playerNo.inc();

    }
}
