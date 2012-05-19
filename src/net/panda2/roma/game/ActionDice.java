package net.panda2.roma.game;

import net.panda2.game.dice.Dice;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionDice extends Dice {
    boolean unused;
    public ActionDice(int n) {
        super(n);
        unused=false;
    }

    public int roll() {
        super.roll();
        unused=true;
        return score;
    }

    public void use() {
        unused=false;

    }
    public boolean isUsed() {
        return (!unused);
    }
    public boolean isValid() {
        return(unused);
    }
    public DiceView getDiceView() {
        return new DiceView(score, unused);

    }

    int fiddle(int amt, boolean unused) {
        score+=amt;
        this.unused = unused;
        return score;
    }

    public void setUsed() {
        unused = false;
    }
}