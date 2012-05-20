package net.panda2.roma.action;

import net.panda2.RingInteger1;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class TakeCardAction extends RomaAction {
    public TakeCardAction(RingInteger1 diceVal) {
        super(diceVal.asInt());
    }

}
