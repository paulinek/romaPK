package net.panda2.roma.action;

import net.panda2.RingInteger0;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class LayCardAction extends RomaAction {
    boolean free;


    public LayCardAction(int handCardNo, int discNo, boolean free) {
        super(handCardNo, discNo);
        this.free = free;
    }

    public LayCardAction(RingInteger0 handCardNo, RingInteger0 discNo) {
        this(handCardNo.asInt(), discNo.asInt(),false);
    }



    public boolean isFree() {
        return free;
    }

}
