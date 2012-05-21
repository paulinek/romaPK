package net.panda2.roma.game;

import net.panda2.RingInteger;
import net.panda2.RingInteger0;
import net.panda2.game.card.Tableau;
import net.panda2.game.dice.DiceCollection;
import net.panda2.roma.card.PJRomaCard;
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




    PlayerState currentPlayer() {
        return player[playerNo.get()];
    }

    PlayerState getNextPlayer () {
        return player[playerNo.next()];

    }

    private RomaGameState(RomaRules ruleSet, GameEngine ge) {
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

        discard = new ViewableCardDeck();
        maindeck = new ViewableCardDeck(discard,true);

        FredCardFactory.createInitialCards(maindeck);
        battleDice = new DiceCollection();  // TODO - parameterise this
        //
        }



    public static RomaGameState createGameState(RomaRules rules, GameEngine gameEngine) {
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
