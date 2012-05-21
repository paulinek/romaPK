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
    //0
    int discNo;
int cost;
    // 0-5
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
        this.diceVal = diceVal-1;
    }
    protected RomaAction(RingInteger1 diceVal) {
        cost=0;
        this.diceVal = diceVal.asInt()-1;
    }

    protected  RomaAction(RingInteger1 diceVal, RingInteger0 discno) {
        cost=0;
        this.diceVal = diceVal.asInt()-1;
        this.discNo = discno.asInt();
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
        return new RingInteger0(diceVal).toR1();
    }

    public void setDiscNo(RingInteger0 whichDiceDisc) {
                discNo = whichDiceDisc.asInt();
    }

    public void setDiceVal(RingInteger0 whichDiceDisc) {
        diceVal = whichDiceDisc.asInt();
    }
}
