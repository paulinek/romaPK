package net.panda2.roma.card.cards;

import framework.interfaces.activators.EssedumActivator;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasicActivator extends PJRomaActivator implements EssedumActivator {
    public BasicActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
    }
}
