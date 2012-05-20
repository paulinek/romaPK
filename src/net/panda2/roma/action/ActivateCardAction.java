package net.panda2.roma.action;

import net.panda2.RingInteger0;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivateCardAction extends RomaAction {
    public ActivateCardAction(RingInteger0 diceNo, RingInteger0 discNo) {

        super(diceNo.asInt());

        this.discNo = discNo.asInt();
    }
}
