package net.panda2.roma.game;

import net.panda2.RingInteger;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.*;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.card.cards.Turris;
import net.panda2.roma.game.exception.RomaException;
import net.panda2.roma.game.exception.RomaGameEndException;
import net.panda2.roma.game.exception.RomaUnAuthException;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameEngine {
    RomaGameState gs;
    RomaRules ruleSet;
    AuthToken masterToken;
    AuthToken currentToken;
    PlayerInteractor playerInput;


    // This class contains the game logic for the game
    // The only state stored in this class pertains to actually running the game
    // eg authentication tokens and an interactor class
    // all other state related to the game is inside RomaGameState gs
    // which also has per player state in gs.player[] (PlayerState)

    // constructors
    // we use the factory design pattern here
    GameEngine(AuthToken tk, PlayerInteractor input) {
        this.masterToken = tk;
        this.playerInput = input;

    }
    // the authtoken here is the master token
    // it is primarily for testing purposes so that the test mechanism can always have a back door
    public static GameEngine createGameEngine(AuthToken tk, PlayerInteractor input) {
         return new GameEngine(tk, input);
    }
    // the standard way of running the game.  uses the console interactor
    public static GameEngine createGameEngine() {
        return createGameEngine(null, new PlayerInteractorConsole());
    }

    // the authentication mechanism
    // two tokens - the master token and the current token
    // cards get given the current token to access the game via the public interface
    // the master token is for debugging
    // otherwise the interface is essentially readOnly
    // makes sure that people aren't trying to fiddle by forging tokens

    // some authenticated getters
    // because the cards are in a different package, they only have access to the public functions in
    // this package
    // all cross package communication is authenticated with a token
    // so that players (which may need to have access to the gameengine object) cannot call any destructive or privacy invading
    // public functions

    public boolean authenticateToken(AuthToken tk) {
        return(tk.equals(masterToken) || tk.equals(currentToken)) ;
    }
    public void authenticateOrDie(AuthToken tk) throws RomaException{
        if(!authenticateToken(tk)) {
            throw new RomaUnAuthException();
        }
    }
    <S> S authenticatedReturn(AuthToken tk, S s) {
        return (authenticateToken(tk))?s : null;
    }

    // these two functions are create and create-and-run
    public void createGame() {
        ruleSet = new RomaRules();
        gs = RomaGameState.createGameState(ruleSet, this);

    }
    public void newGame() {

        // set up the initial deck
        createGame();
        runGame();
    }


    /*****************************
     * Game sequencing logic
     *
     * the only public method is runGame
     * which is usually called via NewGame above
     *****************************/

    public void runGame() {
        initialPhase();
        try{
            while(!gs.gameOver) {
                phaseOne();
                phaseTwo();
                phaseThree();
                gs.nextPlayerTurn();
            }
        }  catch (RomaGameEndException e) {

            // the game is over!

            endOfGame();
        }
    }
    void endOfGame() {
        PlayerState winnerSoFar = null;
        for(PlayerState p:gs.player) {
            if(winnerSoFar == null) {
                winnerSoFar = p;
            } else if(winnerSoFar.vp.amount < p.vp.amount) {
                winnerSoFar = p;
            }
        }
        if(winnerSoFar != null) {
            playerInput.say(winnerSoFar.playerName + " WINS THE GAME");
        }
    }

    // phases
    // phase one has no user input
    // phase two asks the user if they want to reroll under certain circumstances
    // phase 3 lets the user do actions

    void initialPhase() {
        gs.maindeck.shuffleDeck();
        initialDeal();
        initialTrade();
        initialLay();

    }
    void initialLay() {
        //To change body of created methods use File | Settings | File Templates.
        List<LayCardAction> cards[ ] = new List[ruleSet.numPlayers];

        for(int p = 0; p < ruleSet.numPlayers; p++) {
            playerInput.say(player(p).getPlayerName() + "'s turn");
            cards[p] = new ArrayList<LayCardAction>();
                try {
                    while(cards[p].size()>0)         {
                        LayCardAction action = (LayCardAction) playerInput.getLayCardAction("Which card to lay?", gs.player[p].hand, gs.player[p].fields, true);

                        doAction(player(p),action);
                    }
                } catch (RomaException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }
    }
    void initialDeal() {
        for(int i = 0; i < ruleSet.playerInitCards; i++) {
            for(int p = 0; p < ruleSet.numPlayers; p++) {
                dealTo(gs.player[p]);
            }
        }
    }
    void initialTrade() {
        List<Integer> []cardChoices = new List[ruleSet.numPlayers];
        for(int p=0; p < ruleSet.numPlayers; p++) {

            cardChoices[p] = playerInput.selectNCards("Player "+  p + ": Which cards would you like to trade", gs.player[p].hand, ruleSet.playerInitTrade);

        }
        RingInteger rp = new RingInteger0(0,ruleSet.numPlayers);
        for(int p = 0; p < ruleSet.numPlayers; p++) {
            gs.player[p].hand.giveTo(gs.player[rp.set(p).next()].hand, cardChoices[p]);
        }
    }

    void phaseOne() throws RomaGameEndException {
        PlayerState p = gs.currentPlayer();
        // numEmpty returns how many of the diceDisc card slots are empty
        // p transfers that many VPs to the central stockpile
       p.vp.transferAway(gs.tabletopVPStockpile, p.fields.numEmpty());
    }
    void phaseTwo() {
        PlayerState p = gs.currentPlayer();
        boolean playerHappy=false;

        while(!playerHappy) {
                p.dice.roll();
            if(p.dice.allSame()) {
                playerHappy=playerInput.playerQuestionDiceHappy(p.dice);
            } else {
               // you will roll your dice, comrade, and LIKE THEM!
                playerHappy = true;
            }
        }
    }
    void phaseThree() throws RomaGameEndException {
        boolean ended = false;
        while(!ended) {
        PlayerGameView gv = new PlayerGameView(gs);
            try {
                 ended = playerPhaseThree(gv, gs.currentPlayer());
            }
            catch
             (RomaException e) {
                if(e instanceof RomaGameEndException) {
                    throw (RomaGameEndException) e;
                }
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    boolean playerPhaseThree(PlayerGameView gv, PlayerState playerState) throws RomaException {
        ActionData da = null;
        playerInput.printPlayerGameView(gv);
        RomaAction action = playerInput.getAction(gv,playerState);
        if(action != null) {
            if(action instanceof
         EndTurnAction) {
                return true;
        } else {
                doAction(playerState, action);
            }

        }
        return false;
    }

    /*****************************
     * Activation logic
     * mainly for the phase 3 actions
     * RomaAction children are passed around to describe what the action takes
     */

    // doActivateActionFree - the guts of doAction after charging the player
    // (either by using a diceDisc, or subtracting sestertii) is done

    void doActivateActionFree(PJRomaCard  c, RingInteger1 diceVal, ActivateCardAction action) throws RomaException {
        c.activate(this, masterToken, action.getActionData());

    }

    // use up the die, then pass to the free version
    // this is separated out so that the CardActivators in the acceptance testing framework can call it

    void doActivateAction(PlayerState player, RingInteger1 diceVal, ActivateCardAction action) throws RomaException {
        player.useupDiceByVal(action.getDiceVal());
        PJRomaCard c = player.fields.get(diceVal.toR0());

        doActivateActionFree(c,diceVal,action);
    }

    // entry point for scaenarius to call its mimicked card
    void mimicCard(AuthToken tk,  PJRomaCard c, RingInteger1 diceVal, ActivateCardAction action) throws RomaException {
     if(authenticateToken(tk)) {
         doActivateActionFree(c,diceVal,action);
     }
    }


    // this is a Dispatcher function for the RomaAction that is either provided by the interactor
    // or by the test interface
     void doAction(PlayerState playerState, RomaAction action) throws RomaException {
        if(action instanceof ActivateCardAction) {
            doActivateAction(playerState, action.getDiceVal(), (ActivateCardAction) action);
        } else if(action instanceof LayCardAction) {
            int cost;
            if(((LayCardAction) action).isFree()) {
                cost = 0;
            } else {
                cost = playerState.hand.getCard(action.getCardNo()).getPrice();
            }
            playerState.layCard(action.getCardNo(), action.getDiscNo());
            playerState.money.transferAway(gs.treasury, cost);
        } else if(action instanceof TakeCardAction) {
            playerState.useupDiceByVal(action.getDiceVal());

            int nCards = action.getDiceVal().asInt();
            ViewableCardDeck deck = gs.maindeck.dealCard(nCards);
            RingInteger0 choice = playerInput.chooseTakeCardCard(deck);
            deck.giveTo(playerState.hand, choice);
            deck.discardTo(gs.discard);

        } else if(action instanceof  TakeMoneyAction) {
            playerState.useupDiceByVal(action.getDiceVal());
            int amount = action.getDiceVal().asInt();
            gs.treasury.transferAway(playerState.money,amount   );

        } else if(action instanceof EndTurnAction) {
            // should never get here!
         }



    }


    // public interface
    // helper functions for card actions to call

    public RomaGameState getGameState(AuthToken tk) {
        return authenticatedReturn(tk, gs);
    }

    public PlayerState getCurrentPlayer(AuthToken tk) {
        return authenticatedReturn(tk, gs.currentPlayer());
    }

    public PlayerState getNextPlayer(AuthToken tk) {
        return authenticatedReturn(tk, gs.getNextPlayer());
    }


    // this is the generic battle function.
    // it checks the target dice disc's card vs the attack roll
    // and destroys the card successful
    // attackRoll is passed in rather than rolled internally to allow for the
    // centurion card to modify the attack roll
    public void battleCard(AuthToken tk, int attackRoll, RingInteger0 cardLocation) {
        if(authenticateToken(tk)) {
            battleCard(attackRoll,cardLocation);
        }
    }
    public void activateCard(AuthToken tk, PlayerState player, ActivateCardAction action) {
        if(!authenticateToken(tk)) {
            return;
        }
        checkNotNull(action);
        checkNotNull(player);
        RingInteger1 diceVal = action.getDiceVal();

        try {
            doActivateAction(player, diceVal, action);
        } catch (RomaException e) {
            if(e instanceof RomaGameEndException) {
                gs.gameOver=true;
            } else {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
    public void activateBribe(AuthToken tk, PlayerState player, ActivateCardAction action) {
        if(!authenticateToken(tk)) {
            return;
        }
        checkNotNull(action);
        checkNotNull(player);

        RingInteger1 diceVal = action.getDiceVal();
        try {
            PJRomaCard c = player.fields.get(action.getDiscNo());
            // use up the action dice
            player.useupDiceByVal(diceVal);
            // take away money
            player.money.transferAway(gs.treasury, c.getPrice());
            // do the actual card's action
            doActivateActionFree(c,diceVal,action);

        } catch (RomaException e) {
            if(e instanceof RomaGameEndException) {
                gs.gameOver=true;
            } else {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }


    // some getters and pluckers
    public void takeDiscardCard(AuthToken tk, PlayerState player, RingInteger0 discardIndex) {
        if(authenticateToken(tk)) {
            gs.discard.giveTo(player.hand,discardIndex);
        }
    }

    public void takeDeckCard(AuthToken tk, PlayerState player, RingInteger0 discardIndex) {
        if(authenticateToken(tk)) {
            gs.maindeck.giveTo(player.hand,discardIndex);
        }
    }

    public int countDiscards() {
        return gs.discard.size();
    }
    public int countDeckCards() {
        return gs.maindeck.size();
    }

    public boolean takeVPs(AuthToken tk, PlayerState currentPlayer, int numVPs) throws RomaGameEndException {
        if(authenticateToken(tk)) {
            gs.tabletopVPStockpile.transferAway(currentPlayer.vp,numVPs );
            return true;
        }
        return false;
    }




    void dealTo(PlayerState p) {
        p.hand.addCard(gs.maindeck.dealCard());
    }
    PlayerState player(int p) {
        return gs.player[p];
    }


    // this just rolls the battle die
    public int rollBattle() {
        return gs.battleDice.roll();

    }


    // card combat and discarding
    void battleCard(int attackRoll,RingInteger0 cardLocation) {
        PlayerState opponent = gs.getNextPlayer();
        PJRomaCard opponentCard = opponent.fields.get(cardLocation);
        checkNotNull(opponentCard);
        int totalDefense = opponentCard.getDefense() + opponent.defenseBonus();
        if(opponentCard instanceof Turris) {
            // hack
            // we counted the turris before
            totalDefense--;
        }
        if(attackRoll >= totalDefense) {
            discardCard(opponent, opponent.fields, cardLocation);
        }
    }
    private void discardCard(PlayerState player, RingInteger0 which) {
        discardCard(player, player.fields, which);
    }
    void discardCard(PlayerState player, ViewableTableau t, RingInteger0 which) {
        PJRomaCard c = t.get(which.asInt());

       c.decreaseLives();
        if(c.isDead()) {
        if(player.hasGrimReaper()) {
            t.discard(which, player.hand);
        } else {
            t.discard(which, gs.discard);
        }
        }
    }
    public void discardEnemyCard(AuthToken tk, RingInteger0 which) {
        if(authenticateToken(tk))
            discardCard(gs.getNextPlayer(), which);
    }
    public void discardMyCard(AuthToken tk, RingInteger0 which) {
        if(authenticateToken(tk) ){
            discardCard(gs.currentPlayer(), which);
        }
    }


    // lets you change a die's value
    // primarily for consul to use
    public void fiddleDice(AuthToken tk, RingInteger1 diceVal, int amt) {
        if(authenticateToken(tk)) {
            gs.currentPlayer().dice.fiddle(diceVal, amt);
        }
    }
    // set defense delta
    // for essedum (turris calculated separately)
    public void knockOffDefense(AuthToken tk, int amt) {
        if(authenticateToken(tk)) {
            gs.getNextPlayer().fields.reduceDefense(amt);
        }
    }


    // take card from field to hand
    public void unlayCard(AuthToken tk, boolean me, RingInteger0 opponentCardNo) {

        if(!authenticateToken(tk))
            return;
        PlayerState p = me?gs.currentPlayer():gs.getNextPlayer();

        PJRomaCard c = p.fields.get(opponentCardNo);
        if(c != null) {
            p.hand.addCard(c);
            p.fields.set(opponentCardNo, null);
        }
    }

    public PJRomaCard dealRandomCard(AuthToken tk) {
        if(authenticateToken(tk)) {
            gs.maindeck.shuffleDeck();
            PJRomaCard c = gs.maindeck.dealCard();
            return c;
        }
        return null;
    }

    public    void reshuffleDeck(AuthToken tk) {
        if(authenticateToken(tk)) {
            gs.maindeck.shuffleDeck();
        }
    }

    public void sayToPlayer(AuthToken tk, String s) {
        if(authenticateToken(tk)) {
            playerInput.say(s);
        }
    }

}