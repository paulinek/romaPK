package net.panda2.roma.game;

import framework.interfaces.AcceptanceInterface;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 18/05/12
 * Time: 12:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class PJRomaAcceptanceTester implements AcceptanceInterface {
    /**
     * Return a {@link framework.interfaces.MoveMaker} that will modify the given RomaGameState.
     * <p/>
     * <p>
     * This MoveMaker will be used by the tests to modify the RomaGameState
     * that was given by getInitialState. The affected RomaGameState is
     * included as a parameter so you can ensure that the MoveMaker will
     * operate on the correct RomaGameState.
     * </p>
     *
     * @param state the RomaGameState that the mover will apply changes to
     * @return a MoveMaker that will modify the given RomaGameState
     */
    @Override
    public MoveMaker getMover(GameState state) {
        return new PJRomaTestMoveMaker(state);
    }

    /**
     * Instantiate a {@link framework.interfaces.GameState} object.
     * <p/>
     * <p>
     * The created RomaGameState should be a mutable new instance as this is called
     * before each test is run.
     * </p>
     * <p/>
     * <p>
     * The state should be set in the initial condition as defined per:
     * TODO: add the crap that makes an initial state here.
     * </p>
     *
     * @return a RomaGameState at the initial state
     */
    @Override
    public GameState getInitialState() {
        // this creates an authtoken and gameengine for us
        PJRomaTestGameState gst = new PJRomaTestGameState();
        return gst;

    }
}
