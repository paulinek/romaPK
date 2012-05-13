package card;
import Roma.RomaException; import Roma.RomaInvalidMoveException;

import java.util.Collections;
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
       public CardDeck() {
           // a card deck is a stack of cards which can be sorted and then dealt from
           cards = new Vector<Card>();
       }
        public CardDeck(Vector<Card> c) {
            cards = c;
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
    public CardDeck makeActionDeck(Vector<Integer> cardRefs) throws RomaException {
        // make sure all the card references we are given are valid

        for(int i = 0; i < cardRefs.size(); i++) {
            Integer I = cardRefs.elementAt(i);
            if(I.intValue() >= cards.size()) {
                    throw new RomaInvalidMoveException();        }
        }
    }
}
