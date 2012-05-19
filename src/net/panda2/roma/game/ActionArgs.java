package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 5:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActionArgs {
    public ActionArgs(int cardNo, int diceNo, int discNo) {
        this.cardNo = cardNo;
        this.diceNo = diceNo;
        this.discNo = discNo;
    }


    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public int getDiceNo() {
        return diceNo;
    }

    public void setDiceNo(int diceNo) {
        this.diceNo = diceNo;
    }

    public int getDiscNo() {
        return discNo;
    }

    public void setDiscNo(int discNo) {
        this.discNo = discNo;
    }

    int cardNo=0, diceNo=0, discNo=0;

}
