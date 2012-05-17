package net.panda2.roma.game;

import net.panda2.roma.card.cards.*;

public class CardFactory {

	public static void createInitialCards(ViewableCardDeck d) {
        d.addNcards(2, new Legat( 5, 2));
        d.addNcards(1, new Sicarius(9,2));
        d.addNcards(2, new Architectus(3,4));
        d.addNcards(2, new Consiliarius(4,4));
        d.addNcards(2, new Gladiator(6,5));
        d.addNcards(1, new Mercator(7,2));
        d.addNcards(2, new Consul(3,3));
        d.addNcards(3, new Legionarius(4,5));
        d.addNcards(1, new Nero(8,9));
	    d.addNcards(2, new Haruspex(4,3));
        d.addNcards(2, new Velites(5,3));
        d.addNcards(2, new Essedum(6,3));
        d.addNcards(2, new TribunusPlebis(5,5));
        d.addNcards(2, new Centurio(9,5));
    }
}
