package net.panda2.roma.game;

import net.panda2.game.dice.DiceCup;

import java.util.ArrayList;
import java.util.List;

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
   return d.fiddle(amt);
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
}
