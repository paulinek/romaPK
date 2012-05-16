package net.panda2.roma;

import net.panda2.roma.game.GameEngine;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 16/05/12
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class RomaGame{

    public static void main(String[] args) {
        GameEngine ge = GameEngine.createGameEngine();
        ge.newGame();
    }
}
