package tests.verified;

import framework.Test;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * 
 * Test if the player advance each turn, using a loop
 * 
 * @author Junjie CHEN
 *
 */

public class TurnAdvanceTest extends Test {

    private static final int NUM_TESTS = 1000;
    
    @Override
    public String getShortDescription() {
        return "Checking if player advances at end of turn.";
    }

    @Override
    public void run(GameState gameState, MoveMaker move)
                                          throws AssertionError,
                                          UnsupportedOperationException,
                                          IllegalArgumentException {

        out.println("Testing if the player advances");

        gameState.setWhoseTurn(0);

        for(int i = 0; i < NUM_TESTS; i++) {
            assert(gameState.getWhoseTurn() == (i % 2));
            move.endTurn();
        }
    }
}
