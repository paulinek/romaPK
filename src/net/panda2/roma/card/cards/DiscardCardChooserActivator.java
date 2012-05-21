package net.panda2.roma.card.cards;

import framework.interfaces.activators.AesculapinumActivator;
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
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class DiscardCardChooserActivator extends PJRomaActivator implements AesculapinumActivator, HaruspexActivator {
    public DiscardCardChooserActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(card, gst, p, a);
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
        // indexes run the opposite way

        getData().stackpush(new RingInteger0(getGE().countDiscards() - 1 - indexOfCard));
    }
}
