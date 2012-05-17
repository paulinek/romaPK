package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class DiceView {
    boolean active;
    int score;

    DiceView(int score, boolean unused) {
    this.active = unused;
    this.score=score;
    }

    public boolean isActive() {
        return active;
    }

    public int getScore() {
        return score;
    }

    public String getPrintable() {

    return "("+score+")"+(active?"":"used");
    }
}
