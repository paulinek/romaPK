package net.panda2.roma.action;

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

    public LayCardAction(int handCardNo, int discNo) {
        super(handCardNo, discNo);
        this.free = false;
    }
    public boolean isFree() {
        return free;
    }

}
