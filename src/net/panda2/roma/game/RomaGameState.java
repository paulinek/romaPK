package net.panda2.roma.game;

import net.panda2.RingInteger;
import net.panda2.RingInteger0;
import net.panda2.game.card.Tableau;
import net.panda2.game.dice.DiceCollection;
import net.panda2.roma.card.PJRomaCard;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:29 PM
 */

/********************************
 * This class encapsulates the global state of a game of Roma
 * ie the state which is not per-player
 * There are no arrays in this class except player[] because
 * any per-player information should go into the playerstate.
 */
public class RomaGameState {
    int numPlayers;
    PlayerState player[];
    RingInteger playerNo;

    boolean gameOver;

    // accountant factories for money and vp
    StashFactory<VPStash> vpStash;
    StashFactory<MoneyStash> moneyStash;
    // the bank reserves for VPs and sestertii
    VPStash tabletopVPStockpile;
    MoneyStash treasury;

    // card decks - deal and discard
    ViewableCardDeck maindeck, discard;

    // keep a reference to these in case we need them later
    GameEngine ge;
    RomaRules ruleset;

    Tableau<PJRomaCard> diceDiscs;
    DiceCollection battleDice;


    /***********************************
     * A lot of this constructor is about translating the numbers in the RomaRules class
     * into an actual initial board state
     *
     * */
    private RomaGameState(RomaRules ruleSet, GameEngine ge) {

        this.ge = ge;
        this.ruleset = ruleset;

        //ref rules for numPlayers
        numPlayers=ruleSet.numPlayers;

        // player is an array of size numPlayers
        player=new PlayerState[numPlayers];

        // RingInteger expects the maximum valid number, so 0..numPlayers-1
        playerNo =new RingInteger0(0,numPlayers);
        // create a new net.panda2.roma.game.PlayerState object for each of numPlayers
        int i;

        // set up tabletop card decks
        discard = new ViewableCardDeck();
        maindeck = new ViewableCardDeck(discard,true);

        // create stashes (so that piles can be created later
        vpStash = new StashFactory<VPStash>(ruleSet.gameTotalVP, VPStash.createStash(0,0));
        moneyStash = new StashFactory<MoneyStash>(Integer.MAX_VALUE, MoneyStash.createStash(0,0));

        // seed stockpile with init VP
        tabletopVPStockpile=allocate("VP",ruleSet.tableInitVP, ruleSet.minVP);
        treasury = allocate("Money", Integer.MAX_VALUE-65536);

        // the stashes need to be set up before players so that they can get piles
        for (i=0; i<numPlayers; i++){
            player[i]=new PlayerState(ruleSet, ge,this,"Player"+i);
        }

        // set up the initial cards
        // this should be in the ruleset
        // but I can't work out how to create a reference to a static method
        CardFactory.createInitialCards(maindeck);
        battleDice = new DiceCollection();  // TODO - parameterise this
        //
        }



    public static RomaGameState createGameState(RomaRules rules, GameEngine gameEngine) {
        return new RomaGameState(rules, gameEngine);
    }

    PlayerState currentPlayer() {
        return player[playerNo.get()];
    }

    PlayerState getNextPlayer () {
        return player[playerNo.next()];
    }

    public void setPlayerNo(int player, AuthToken tk) {
        if(ge.authenticateToken(tk))
            playerNo.set(player);
    }

     void nextPlayerTurn() {
        playerNo.inc();
    }
    // internal interface
    <T> T allocate(String type, int amt, int min) {
        StashFactory f = null;
        if(type.equals("VP"))
            f = vpStash;
        else if(type.equals("Money"))
            f = moneyStash;

        return (T) f.make(amt,min);

    }
    <T> T allocate(String s, int amt) {
        return allocate(s, amt);
    }

}
