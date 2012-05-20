package net.panda2.roma.card.cards;

import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.ScaenicusActivator;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScaenicusCardActivator extends PJRomaActivator implements ScaenicusActivator {
    public ScaenicusCardActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
    }

    /**
     * Select a card to mimic with the Scaenicus.
     * <p/>
     * <p>
     * This method selects a card for an activated Scaenicus to mimic.
     * A new CardActivator corresponding to the chosen card is returned,
     * so the test may use it to activate the selected card.
     * </p>
     *
     * @param diceDisc
     * @return
     */
    @Override
    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
