package net.panda2.roma.card.cards;

import framework.interfaces.activators.ConsulActivator;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsulCardActivator extends PJRomaActivator implements ConsulActivator {
    private int fiddleAmt;

    public ConsulCardActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
    }

int dice;
    /**
     * Choose the amount a dice disc value changes by.
     * <p/>
     * <p>
     * Valid changes are -1 or 1 in the current game.
     * </p>
     *
     * @param amount the amount to change by.
     */
    @Override
    public void chooseConsulChangeAmount(int amount) {
        fiddleAmt=amount;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void chooseWhichDiceChanges(int originalRoll) {
dice=originalRoll;
        //To change body of implemented methods use File | Settings | File Templates.
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
        getData().stackpush(new RingInteger1(dice).toR0());
        // this is a giant hack
        // basically, RingInteger0 can't be negative
        // so we push fiddleAmt+1
        // and subtract 1 on the far side
        getData().stackpush(new RingInteger0(fiddleAmt+1));//To change body of implemented methods use File | Settings | File Templates.
        // at this point, top of stack has fiddle amount
        super.complete();
    }
}
