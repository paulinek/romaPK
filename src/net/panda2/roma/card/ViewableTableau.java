package net.panda2.roma.card;

import net.panda2.game.card.Tableau;
import net.panda2.roma.game.ViewableCardCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewableTableau extends Tableau<PJRomaCard> implements ViewableCardCollection{
    public ViewableTableau(int slots) {
        super(slots);
    }
    public
    List<CardView>
    getCardView() {
        List<CardView> cv = new ArrayList<CardView>();
        for(PJRomaCard c:cards) {
            if(c == null) {
                cv.add(new NullCardView());
            } else {
                cv.add(c.makeCardView());
            }
        }
        return cv;
    }

    public void reduceDefense(int amt) {
        for(PJRomaCard c: cards) {
                if(c != null) {
                     c.defenseOffset -= 2;
                }
        }
    }
}
