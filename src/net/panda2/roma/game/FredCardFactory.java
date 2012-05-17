package net.panda2.roma.game;

import net.panda2.roma.card.cards.*;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/05/12
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class FredCardFactory extends CardFactory {
    public static void createInitialCards(ViewableCardDeck d) {
    CardFactory.createInitialCards(d);

        d.addNcards(100, new FredHilmer(1, 99));
    }
}
