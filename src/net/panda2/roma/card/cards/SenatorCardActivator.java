package net.panda2.roma.card.cards;

import framework.cards.Card;
import framework.interfaces.activators.SenatorActivator;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class SenatorCardActivator extends PJRomaActivator implements SenatorActivator {
    public SenatorCardActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
    }

    @Override
    public void layCard(Card myCard, int whichDiceDisc) {

    }

    /**
     * Mark the pending activation as complete.
     * <p/>
     * <p>
     * This method must be called when an activation is complete.
     * This method cannot be called until all required activation
     * methods have been called. No other methods in the move maker can
     * be called after a CardActivator has been received until after its
     * complete method is called. This is really important.
     * </p>
     */
    @Override
    public void complete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
