package net.panda2.roma.action;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivateCardAction extends RomaAction {
    public ActivateCardAction(int diceNo, int discNo) {
        super(diceNo);
        this.discNo = discNo;
    }
}
