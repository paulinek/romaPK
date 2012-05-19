package net.panda2.roma.game;

import net.panda2.RingInteger;
import net.panda2.roma.action.*;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaException;
import net.panda2.roma.game.exception.RomaUnAuthException;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkElementIndex;

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
    PlayerInteractor playerInput;

    public GameEngine(AuthToken tk, PlayerInteractor input) {
        this.masterToken = tk;
        this.playerInput = input;

    }

    public static GameEngine createGameEngine() {

        return new GameEngine(null, new PlayerInteractorConsole());
    }
    public static GameEngine createGameEngine(AuthToken tk, PlayerInteractor input) {
         return new GameEngine(tk, input);
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


    public void createGame() {
        ruleSet = new RomaRules();
        try {
            gs = RomaGameState.createGameState(ruleSet, this);
        } catch(RomaException e) {


        }
    }
    public void newGame() {

        // set up the initial deck
        createGame();
        RunGame();
    }

    Stash allocateVPs(int amt) {
        return gs.vpStash.make(amt);
    }

    public void RunGame() {
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

    private void endOfGame() {
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


    void dealTo(PlayerState p) {
        p.hand.addCard(gs.maindeck.dealCard());
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

    private void initialLay() {
        //To change body of created methods use File | Settings | File Templates.
        List<LayCardAction> cards[ ] = new List[ruleSet.numPlayers];

        for(int p = 0; p < ruleSet.numPlayers; p++) {
            playerInput.say("Player " + p + "'s turn");
            cards[p] = new ArrayList<LayCardAction>();
            for(int i = 0; i < ruleSet.playerInitCards; i++) {
                try {
                    doAction(gs.player[p],playerInput.getLayCardAction("Which card to lay?", gs.player[p].hand, gs.player[p].diceDiscCards, true));
                } catch (RomaException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
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
        RingInteger rp = new RingInteger(ruleSet.numPlayers-1);
        for(int p = 0; p < ruleSet.numPlayers; p++) {
            gs.player[p].hand.giveTo(gs.player[rp.set(p).next()].hand, cardChoices[p]);
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

     void doAction(PlayerState playerState, RomaAction action) throws RomaException {

        if(action instanceof ActivateCardAction) {
            playerState.useupDice(action.getDiceNo());
            PJRomaCard c = playerState.diceDiscCards.get(action.getDiceNo());

            c.activate(this, masterToken, action.getActionData());

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
            playerState.useupDice(action.getDiceNo());

            int nCards = playerState.dice.getNth(action.getDiceNo());
            ViewableCardDeck deck = gs.maindeck.dealCard(nCards);
            int choice = playerInput.chooseTakeCardCard(deck);
            deck.giveTo(playerState.hand, choice);
            deck.discardTo(gs.discard);

        } else if(action instanceof  TakeMoneyAction) {
            playerState.useupDice(action.getDiceNo());
            int amount = playerState.dice.getNth(action.getDiceNo());
            gs.treasury.transferAway(playerState.money,amount   );

        } else if(action instanceof EndTurnAction) {
            // should never get here!
         }



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

    public RomaGameState getGameState(AuthToken tk) {
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
        checkElementIndex(opponentCardNo,ruleSet.numDiceDiscs);
        if(!authenticateToken(tk))
            return;
        PlayerState p = me?gs.currentPlayer():gs.getNextPlayer();

        PJRomaCard c = p.diceDiscCards.get(opponentCardNo);
        if(c != null) {
            p.hand.addCard(c);
            p.diceDiscCards.set(opponentCardNo, null);
        }
    }

    public PlayerState getNextPlayer(AuthToken tk) {
        return authenticatedReturn(tk, gs.getNextPlayer());
    }
}
