package net.tests.verified;

import framework.Test;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * Testing the basic mechanics of victory point addition and removal.
 * @author Damon (Stacey damon.stacey)
 */
public class InitialisationActionDieBasicTest extends Test {

   @Override
   public String getShortDescription() {
      return "Checking all action dice can be set and recieved accurately..";
   }

   @Override
   public void run(GameState gameState, MoveMaker move)
                                          throws AssertionError,
                                          UnsupportedOperationException,
                                          IllegalArgumentException {

      /*
         public int[] getActionDice ();
         
         public void setActionDice (int[] dice);
      */
   }
}
