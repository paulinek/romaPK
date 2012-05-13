package gameEngine;

import Roma.AuthToken;
import Roma.RomaException;

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
   

    private GameEngine() {
    }

    // makes sure that people aren't trying to fiddle by forging tokens
    public boolean authenticateToken(AuthToken tk) {
        return true;
    }


    public static GameEngine createGameEngine() {
        
        return new GameEngine();
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

    private void RunGame() {

    }
}
