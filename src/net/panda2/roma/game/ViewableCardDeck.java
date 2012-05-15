package net.panda2.roma.game;

import net.panda2.game.card.CardDeck;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.NullCardView;
import net.panda2.roma.card.PJRomaCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewableCardDeck extends CardDeck<PJRomaCard> {
    public ViewableCardDeck(AuthToken tk) {
        super(tk);
    }


    // this is because java doesn't let you deprotect stuff.

    PJRomaCard dealCard()  {
        return deal();
    }
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

    public ViewableCardDeck() {
    }

    public ViewableCardDeck(Vector<PJRomaCard> c) {
        super(c);
    }

    public List<CardView>
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

     Vector<PJRomaCard> getCards() {
        return cards;
    }

    public int numCards() {
        return cards.size();
    }
}
