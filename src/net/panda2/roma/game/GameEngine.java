package net.panda2.roma.game;

import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.card.ViewableTableau;
import net.panda2.roma.game.exception.RomaException;
import net.panda2.roma.game.exception.RomaUnAuthException;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameEngine {
    GameState gs;
    RomaRules ruleSet;
    AuthToken masterToken;
    PlayerInteractor playerInput;
    public static GameEngine createGameEngine() {

        return new GameEngine();
    }
    public static GameEngine createGameEngine(AuthToken tk) {
        GameEngine ge = new GameEngine();
        ge.masterToken = tk;
        return ge;
    }
    private GameEngine() {
        playerInput = new PlayerInteractor();
    }

    // makes sure that people aren't trying to fiddle by forging tokens
    public boolean authenticateToken(AuthToken tk) {
        return true;
    }
    public void authenticateOrDie(AuthToken tk) throws RomaException{
        if(!authenticateToken(tk)) {
            throw new RomaUnAuthException();
        }
    }






    public void newGame() {
        ruleSet = new RomaRules();
        try {
        gs = GameState.createGameState(ruleSet, this);
        } catch(RomaException e) {

        }
        // set up the initial deck

        RunGame();
    }

    Stash allocateVPs(int amt) {
        return gs.vpStash.make(amt);
    }

    public void RunGame() {
 try{
     while(!gs.gameOver) {
         phaseOne();
         phaseTwo();
         phaseThree();
         gs.playerNo.inc();
     }
 }  catch (RomaGameEndException e) {
     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    }

    void phaseOne() throws RomaGameEndException {
        PlayerState p = gs.currentPlayer();
        // numEmpty returns how many of the diceDisc card slots are empty
        // p transfers that many VPs to the central stockpile
       p.vp.transferAway(gs.tabletopVPStockpile, p.diceDiscCards.numEmpty());
    }

    void phaseTwo() {
        PlayerState p = gs.currentPlayer();
        boolean playerHappy=false;

        while(!playerHappy) {
                p.dice.roll();
            if(p.dice.allSame()) {
                playerHappy=playerInput.yesOrNo("Are you happy with these dice?");
            } else {
               // you will roll your dice, comrade, and LIKE THEM!
                playerHappy = true;
            }
        }
    }

    void phaseThree() {
        PlayerGameView gv = new PlayerGameView(gs);
    }


    // helper functions for card actions to call

    // some authenticated getters
    // because the cards are in a different package, they only have access to the public functions in
    // this package
    // all cross package communication is authenticated with a token
    // so that players (which may need to have access to the gameengine object) cannot call any destructive or privacy invading
    // public functions

    public <S> S authenticatedReturn(AuthToken tk, S s) {
        return (authenticateToken(tk))?s : null;
    }

    public GameState getGameState(AuthToken tk) {
        return authenticatedReturn(tk, gs);
    }

    public PlayerState getCurrentPlayer(AuthToken tk) {
        return authenticatedReturn(tk, gs.currentPlayer());
    }

    // this just rolls the battle die
    public int rollBattle() {
        return gs.battleDice.roll();

    }
    // this is the generic battle function.
    // it checks the target dice disc's card vs the attack roll
    // and destroys the card successful
    // attackRoll is passed in rather than rolled internally to allow for the
    // centurion card to modify the attack roll
    public void battleCard(int whichDiceDisc, int attackRoll, AuthToken tk) {
        int cardLocation = whichDiceDisc;
        if(attackRoll > gs.getNextPlayer().diceDiscCards.get(cardLocation).getDefense())
            destroyCard(gs.getNextPlayer().diceDiscCards, cardLocation);
    }

    void destroyCard(ViewableTableau t, int which) {
        t.discard(which, gs.discard);

    }
    public void destroyCard(int which, AuthToken tk) {
        destroyCard(which, false, tk);
    }

    public void destroyCard(int whichDiceDisc, boolean b, AuthToken tk) {
        PlayerState p = b?gs.currentPlayer():gs.getNextPlayer();

        if(authenticateToken(tk))
            destroyCard(p.diceDiscCards, whichDiceDisc);

    }

    // lets you change a die's value
    public void fiddleDice(int diceNo, int amt, AuthToken tk) {
        if(authenticateToken(tk)) {
            gs.currentPlayer().dice.fiddle(diceNo, amt);
        }
    }
    public void knockOffDefense(int amt, AuthToken tk) {
        if(authenticateToken(tk)) {
            gs.getNextPlayer().diceDiscCards.reduceDefense(amt);
        }
    }

    public void unlayCard(AuthToken tk, boolean me, int opponentCardNo) {
        checkElementIndex(opponentCardNo,ruleSet.diceDiscs);
        if(!authenticateToken(tk))
            return;
        PlayerState p = me?gs.currentPlayer():gs.getNextPlayer();

        PJRomaCard c = p.diceDiscCards.get(opponentCardNo);
        if(c != null) {
            p.hand.addCard(c);
            p.diceDiscCards.set(opponentCardNo, null);
        }
    }
}
