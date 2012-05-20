package net.panda2.roma.card.cards;

import framework.interfaces.activators.MercatorActivator;
import net.panda2.RingInteger0;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class MercatorCardActivator extends PJRomaActivator implements MercatorActivator {
    public MercatorCardActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
    }

    /**
     * Choose the number of victory points to buy with the Mercator.
     *
     * @param VPToBuy the number of points to buy
     */
    @Override
    public void chooseMercatorBuyNum(int VPToBuy) {
    getData().stackpush(new RingInteger0(VPToBuy));
    }

}
