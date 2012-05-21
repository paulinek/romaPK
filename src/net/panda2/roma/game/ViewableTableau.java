package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.game.card.Tableau;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.NullCardView;
import net.panda2.roma.card.PJRomaCard;

import java.util.ArrayList;
import java.util.Collection;
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
        isBlockedUntil = new int[slots];
    }
    int[] isBlockedUntil;
    void setBlockedUntil(RingInteger1 diceVal, int turn) {
        isBlockedUntil[diceVal.toR0().asInt()] = turn;
    }
    boolean isBlocked(RingInteger1 diceVal, int now) {
        return now < isBlockedUntil[diceVal.toR0().asInt()];
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
                     c.addDefenseOffset(-2);
                }
        }
    }
    void setCard(RingInteger0 disc, PJRomaCard c) {
//        checkArgument(cards.get(disc) == null);
        set(disc.asInt(), c);

    }

    public int getSize() {
        return cards.size();
    }

    public PJRomaCard get(RingInteger1 r) {
        return super.get(r.toR0().asInt());
    }

    public void discard(RingInteger0 which, ViewableCardDeck hand) {
         super.discard(which.asInt(), hand);
    }

    public PJRomaCard get(RingInteger0 cardLocation) {
        int location = cardLocation.asInt();
        return super.get(location);
    }

    public void set(RingInteger0 opponentCardNo, PJRomaCard e) {
        super.set(opponentCardNo.asInt(), e);

    }

    @Override
    public int howManyOfThese(String name) {

        List<CardView> cards = getCardView();
        return countN(cards,name);

    }
    int
    countN(Collection c, Object x) {
        int n=0;
        for(Object o:c) {
            if (o.equals(x)) {
                n++;
            }
        }
        return n;
    }
}
