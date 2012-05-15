package net.panda2.game.dice;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiceCup<D> {
    protected ArrayList<D> dice;
    protected int ndice;
    public DiceCup(int n) {
        ndice=n;
        dice = new ArrayList<D>(n);
    }

    public void roll() {
        for(D d: dice) {

            ((Dice)d).roll();
        }
    }

    D get(int n) {
    checkElementIndex(n, ndice);
    return dice.get(n);
    }

    // are all the dice the same?

    public boolean allSame() {
        int v = ((Dice)dice.get(0)).getScore();
        for(D d:dice) {
            if(((Dice)d).getScore() != v) {
                return false;
            }
        }
        return true;

    }
}
