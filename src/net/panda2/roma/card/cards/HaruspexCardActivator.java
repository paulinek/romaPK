package net.panda2.roma.card.cards;

import framework.interfaces.activators.HaruspexActivator;
import net.panda2.RingInteger0;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class HaruspexCardActivator extends PJRomaActivator implements HaruspexActivator {
    public HaruspexCardActivator(PJRomaCard c, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(c, gst, p, a);
    }

    /**
     * The user chooses a card from a pile.
     * <p/>
     * <p>
     * The tester will get your discard pile, and scan through it for the card required.
     * So, the card they have choosen will be at getDiscard().get(indexOfCard);
     * </p>
     *
     * @param indexOfCard the index of the card to use
     */
    @Override
    public void chooseCardFromPile(int indexOfCard) {
        getData().stackpush(new RingInteger0(getGE().countDeckCards() - 1 - indexOfCard));

        //To change body of implemented methods use File | Settings | File Templates.
    }
}
