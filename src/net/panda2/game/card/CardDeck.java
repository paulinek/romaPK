package net.panda2.game.card;

import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.exception.RomaException;
import net.panda2.roma.game.exception.RomaUnAuthException;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardDeck<C> {
        protected Vector<C> cards;
        AuthToken tk;

        public CardDeck(AuthToken tk) {
            this.tk=tk;
        }

        protected CardDeck() {
           // a card deck is a stack of cards which can be sorted and then dealt from
           cards = new Vector<C>();
       }
        public CardDeck(Vector<C> c) {
            cards = c;
        }

        protected void setCards(AuthToken tk, List<C> newDeck) throws RomaException {
            if(tk == null || tk != this.tk) {
                    throw new RomaUnAuthException();
            }
            Collections.reverse(newDeck);

            cards.removeAllElements();
            for(C c: newDeck) {
                cards.add(c);
            }
        }
       protected void  shuffle() {
           Collections.shuffle(cards);
       }

       protected void seedCards(Vector<C> deck) {
            int i;
            if(isEmpty() ) {
            for(i = 0; i < deck.size(); i++) {
               cards.add(deck.elementAt(i));
            }
            }
        }
        // deal the top card
       protected C deal() throws IndexOutOfBoundsException {

            int s = cards.size();
            if(s == 0) {
                throw new IndexOutOfBoundsException();
            }
            C c = cards.lastElement();
            cards.removeElementAt(s-1);
            return c;

       }

        boolean isEmpty() {
                return cards.size() == 0;
        }

    protected void add(C e) {
        cards.add(e);
    }
}
