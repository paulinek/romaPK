package net.panda2.roma.action;

import net.panda2.RingInteger0;
import net.panda2.RingInteger1;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RomaAction {
    int cardNo;
    int discNo;
int cost;
    private int diceVal;

    public ActionData getActionData() {
        return actionData;
    }

    public void setActionData(ActionData actionData) {
        this.actionData = actionData;
    }

    ActionData actionData;

    protected RomaAction(int diceVal) {
        cost=0;
        this.diceVal = diceVal;
    }

    protected RomaAction(int cardNo, int discNo) {
        cost=0;
        this.cardNo = cardNo;
        this.discNo = discNo;
    }

    protected RomaAction() {
    }



    public RingInteger0 getCardNo() {
        return new RingInteger0(cardNo);
    }

    public RingInteger0 getDiscNo() {
        return new RingInteger0(discNo);
    }

    public RingInteger1 getDiceVal() {
        return new RingInteger1(diceVal);
    }
}
