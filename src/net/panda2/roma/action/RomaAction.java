package net.panda2.roma.action;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RomaAction {
    int diceNo;
    int cardNo;
    int discNo;
int cost;

    public ActionData getActionData() {
        return actionData;
    }

    public void setActionData(ActionData actionData) {
        this.actionData = actionData;
    }

    ActionData actionData;

    protected RomaAction(int diceNo) {
        cost=0;
        this.diceNo = diceNo;
    }

    protected RomaAction(int cardNo, int discNo) {
        cost=0;
        this.cardNo = cardNo;
        this.discNo = discNo;
    }

    protected RomaAction() {
    }


    public int getDiceNo() {
        return diceNo;
    }

    public int getCardNo() {
        return cardNo;
    }

    public int getDiscNo() {
        return discNo;
    }

}
