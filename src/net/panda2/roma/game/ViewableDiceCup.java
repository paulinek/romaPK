package net.panda2.roma.game;

import net.panda2.game.dice.DiceCup;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewableDiceCup extends DiceCup<ActionDice> {

    public List<DiceView> getDiceView() {
        List<DiceView> dv = new ArrayList<DiceView>();
        for(ActionDice d: dice) {
            dv.add(d.getDiceView());
        }
        return dv;
    }

   int fiddle(int diceNo, int amt) {
    ActionDice d = dice.get(diceNo);
   return d.fiddle(amt, true);
   }
   public ViewableDiceCup(int n, int nsides) {
        super(n);
       for(int i = 0; i < n; i++) {
           dice.add(new ActionDice(nsides));
       }
    }
        public
    boolean isNthUsed(int diceNo) {
        return dice.get(diceNo).isUsed();
    }
    public int getNth(int diceNo) {

        return dice.get(diceNo).getScore();
    }

    public void useup(int diceNo) {
        dice.get(diceNo).setUsed();
    }

    public void setDice(int[] dice) {
    checkArgument(dice.length == this.dice.size());
    for(int i = 0; i < dice.length; i++) {
        this.dice.get(i).fiddle(dice[i],true);
    }
    }

    public int getDiceNoFromValue(int diceToUse) {
        for(int i = 0; i< dice.size(); i++)
        {
            if(dice.get(i).getScore()==diceToUse && dice.get(i).isUsed() == false) {
                return i;
            }
        }
        return -1;
    }

    public int getNDice() {
         return dice.size();
    }
}
