package net.tests.borderline;

import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Testing the basic mechanics of Legat.
 *
 * @author Karla Burnett (karla.burnett)
 */
public class CardActivatorLegatBasicTest extends Test {

    @Override
    public String getShortDescription() {
        return "Checking the basic mechanics of Legat";
    }

    @Override
    public void run(GameState gameState, MoveMaker move)
                                          throws AssertionError,
                                          UnsupportedOperationException,
                                          IllegalArgumentException {
        Card [] ourSide = {Card.NOT_A_CARD, 
                                Card.NOT_A_CARD, 
                                Card.NOT_A_CARD, 
                                Card.NOT_A_CARD, 
                                Card.NOT_A_CARD, 
                                Card.NOT_A_CARD, 
                                Card.NOT_A_CARD};
                                
        // Place 5 cards on the opponent's side
        gameState.setPlayerCardsOnDiscs(0, ourSide);
        // Set up the player stats
        gameState.setPlayerVictoryPoints(0, 10);
        gameState.setPlayerVictoryPoints(1, 10);
        gameState.setPlayerSestertii(0, 5);
        gameState.setPlayerSestertii(1, 0);

        // Set up the game state for the test
        gameState.setWhoseTurn(0);
        gameState.setActionDice(new int [] {3, 3, 4});
        
        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.LEGAT);
        gameState.setPlayerHand(0, hand);
                
        // Place the Legat on disc 3 and activate it
        move.placeCard(Card.LEGAT, 3);
        move.chooseCardToActivate(3).complete();
        
        
        // Check that player 0 has gained 7 victory points, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 17);
        assert(gameState.getPlayerVictoryPoints(1) == 10);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 0);
        
        Card [] opponentSide = {Card.LEGIONARIUS, 
                                Card.AESCULAPINUM, 
                                Card.NOT_A_CARD, 
                                Card.CONSUL, 
                                Card.NOT_A_CARD, 
                                Card.ESSEDUM, 
                                Card.MACHINA};
                                
        // Place 5 cards on the opponent's side
        gameState.setPlayerCardsOnDiscs(1, opponentSide);
                                              
        // Activate the Legat again
        move.chooseCardToActivate(3).complete();
        
        // Check that player 0 has gained 2 victory points, but player
        // 1's score has not changed
        assert(gameState.getPlayerVictoryPoints(0) == 19);
        assert(gameState.getPlayerVictoryPoints(1) == 10);
        assert(gameState.getPlayerSestertii(0) == 0);
        assert(gameState.getPlayerSestertii(1) == 0);
    }
}
