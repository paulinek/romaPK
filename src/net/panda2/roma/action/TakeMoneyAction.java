package net.panda2.roma.action;

import net.panda2.RingInteger1;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class TakeMoneyAction extends RomaAction {

    public TakeMoneyAction(RingInteger1 diceVal) {
        super(diceVal.asInt());
    }

    public TakeMoneyAction(boolean test, int diceval) {

        //To change body of created methods use File | Settings | File Templates.
    }
}
