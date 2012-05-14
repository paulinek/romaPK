package net.panda2.game.card;

import net.panda2.roma.card.NullCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.RomaException;
import net.panda2.roma.game.RomaInvalidMoveException;
import net.panda2.roma.game.RomaUnAuthException;

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
public class CardDeck {
        Vector<Card> cards;
        AuthToken tk;

        public CardDeck(AuthToken tk) {
            this.tk=tk;
        }

        public CardDeck() {
           // a card deck is a stack of cards which can be sorted and then dealt from
           cards = new Vector<Card>();
       }
        public CardDeck(Vector<Card> c) {
            cards = c;
        }

       public Vector<Card> getCards(AuthToken tk) {
           if(tk != null && tk == this.tk) {
               return cards;
           } else {
               return null;
           }
       }

        public void setCards(AuthToken tk, List<Card> newDeck) throws RomaException {
            if(tk == null || tk != this.tk) {
                    throw new RomaUnAuthException();
            }
            Collections.reverse(newDeck);

            cards.removeAllElements();
            for(Card c: newDeck) {
                cards.add(c);
            }
        }
       public void  shuffle() {
           Collections.shuffle(cards);
       }

       public void seedCards(Vector<Card> deck) {
            int i;
            if(isEmpty() ) {
            for(i = 0; i < deck.size(); i++) {
               cards.add(deck.elementAt(i));
            }
            }
        }
        // deal the top card
       Card deal() throws RomaException {
            int s = cards.size();
            if(s == 0) {
                throw new RomaException();
            }
            Card c = cards.lastElement();
            cards.removeElementAt(s-1);
            return c;

       }

        boolean isEmpty() {
                return cards.size() == 0;
        }

    // given a list of cards in the deck, select them out and create an ActionDeck representing the player's actions
    public CardDeck makeActionDeck(List<Integer> cardRefs) throws RomaException {
        // make sure all the card references we are given are valid
        for (Integer I: cardRefs) {
            if(I.intValue() >= cards.size()) {
                    throw new RomaInvalidMoveException();
            }
        }

        CardDeck c = new CardDeck();
        for(Integer I: cardRefs) {
            if(I.intValue() >= 0) {
            c.cards.add(cards.elementAt(I.intValue()));
            } else {
                c.cards.add(new NullCard());
            }
        }


        // now take the cards out of the hand
        // we need to sort this because if we use removeElementAt, the numbers of
        Collections.sort(cardRefs,Collections.reverseOrder());

        for(Integer I: cardRefs) {
            if(I.intValue() >= 0) {
            cards.removeElementAt(I.intValue());
            }
        }

        return c;
    }
}
