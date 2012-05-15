package net.panda2.game.card;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tableau<E extends Card> {
    // This class is essentially a specialised array

    protected ArrayList<E> cards;
    protected int size;
    public Tableau(int slots) {
        cards = new ArrayList<E>(slots);

    }

   public int numEmpty() {
        int n=0;
        for(E e: cards) {
            if(e == null)
               n++;
        }
        return n;
    }
    public E get(int n) {
 checkElementIndex(n, size);

            return(cards.get(n));

         }
    public void set(int n, E e) {
        if(n >= 0 && n < size) {
            cards.set(n,e);
        }

        // fail silently
    }

    public int getIndexOfCard(E e) {
        for(int i=0; i < cards.size(); i++) {
            if(cards.get(i) == e) {
                    return i;
            }
        }
        return -1;
    }

    public void discard(int i, CardDeck discard) {
        E e = cards.get(i);
        cards.set(i, null);
        discard.add(e);
        //To change body of created methods use File | Settings | File Templates.
    }
}
