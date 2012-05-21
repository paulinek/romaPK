package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.game.card.CardDeck;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.NullCardView;
import net.panda2.roma.card.PJRomaCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewableCardDeck extends CardDeck<PJRomaCard> implements ViewableCardCollection {
    private ViewableCardDeck refillSource;

    public ViewableCardDeck() {
        super();
        this.refillSource=null;
    }

    public ViewableCardDeck(Vector<PJRomaCard> c) {
        super(c);
        this.refillSource=null;

    }

    public ViewableCardDeck(ViewableCardDeck refillSource, boolean refill) {

        this();
        this.refillSource = refillSource;
    }



    public List<CardView>    getCardView() {
        List<CardView> cv = new ArrayList<CardView>();
        for(int i = 0; i < cards.size(); i++) {
            PJRomaCard c = cards.get(i);
            if(c == null) {
                cv.add(new NullCardView());
            } else {
                cv.add(c.makeCardView());
            }
        }
        return cv;
    }
    Vector<PJRomaCard> getCards() {
        return cards;
    }
    public int size() {

        return cards.size();
    }

    public PJRomaCard getCard(RingInteger0 i) {
        return cards.get(i.asInt());
    }


    // this is because java doesn't let you deprotect stuff.

    void shuffleDeck() {
        shuffle();
    }

    void addCard(PJRomaCard c) {
        if(c != null)
            cards.add(c);
    }
    void addNcards(int n, PJRomaCard c) {
        int i;
        addCard(c);
        for(i = 1; i < n; i++) {
            add(c.duplicate());
        }
    }

    void setDeck(List<PJRomaCard> deck) {
        cards.removeAllElements();
        for(PJRomaCard c:deck) {
            cards.add(c);
        }
        checkArgument(cards.size() == deck.size());
    }

    PJRomaCard dealCard()  {
        PJRomaCard c=null;
        try {
            c= deal();
        } catch (RomaEmptyDeckException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return c;
    }

    @Override
    protected PJRomaCard deal() throws RomaEmptyDeckException {
        PJRomaCard c=null;
        try {
            c = super.deal();
        } catch(RomaEmptyDeckException e) {
            if(this.refillSource != null && this.refillSource.size() > 0) {
                this.refillSource.discardTo(this);
                this.shuffle();
                c = deal();
            } else {
                throw e;
            }
        }
        return c;
    }

    public ViewableCardDeck dealCard(int nCards) {
        ViewableCardDeck newDeck = new ViewableCardDeck();
        for(int i=0; i < nCards; i++)  {
            try {
                newDeck.addCard(this.deal());
            } catch (RomaEmptyDeckException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return newDeck;
    }

    void giveTo(ViewableCardDeck toHand, List<Integer> cardChoice) {
        Collections.sort(cardChoice, Collections.reverseOrder());
        for(Integer i:cardChoice) {
               giveTo(toHand, new RingInteger0(i));

        }
        //To change body of created methods use File | Settings | File Templates.
    }

    void giveTo(ViewableCardDeck toHand, RingInteger0 I) {
        int i = I.asInt();
        checkElementIndex(i, size());
        PJRomaCard c = cards.get(i);
        cards.remove(i);
        toHand.addCard(c);

    }
    void discardTo(ViewableCardDeck toDeck) {
         RingInteger0 I = new RingInteger0(0);
        for(int i = size()-1; i >= 0; i--) {
            I.set(i);
            giveTo(toDeck, I);
        }
    }

    public void layCard(RingInteger0 cardNo, ViewableTableau tb, RingInteger0 toCardNo) {
        int pos = cardNo.asInt();
        checkElementIndex(pos, size());
        PJRomaCard c = cards.get(pos);
        cards.remove(pos);
        tb.setCard(toCardNo, c);

    }


    // some methods for finding cards
    @Override
    public int howManyOfThese(String name) {
        int n= 0;
        List<CardView> cards = getCardView();
        Collections.frequency(cards,name);
        for(CardView c:getCardView()) {

            if(c.getName().equalsIgnoreCase(name)) {
                n++;
            }
        }
        return n;
    }
    public RingInteger0 findCard(String name) {
        List<CardView> cards = getCardView();
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).equals(name)) {
                return new RingInteger0(i);
            }
        }
        return null;
    }
}
