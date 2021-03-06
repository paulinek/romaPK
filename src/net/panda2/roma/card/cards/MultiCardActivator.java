package net.panda2.roma.card.cards;

import framework.cards.Card;
import framework.interfaces.activators.ArchitectusActivator;
import framework.interfaces.activators.ConsiliariusActivator;
import framework.interfaces.activators.MachinaActivator;
import framework.interfaces.activators.SenatorActivator;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultiCardActivator extends PJRomaActivator implements
        SenatorActivator,ConsiliariusActivator,MachinaActivator,ArchitectusActivator {
    public MultiCardActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(card,gst, p, a);
        ncards=0;
    }
    int ncards;
    @Override
    public void layCard(Card myCard, int whichDiceDisc) {
        getData().stackpush(new CardLocation(myCard.name(), new RingInteger1(whichDiceDisc)));
        ncards++;
    }

    /**
     * Mark the pending activation as complete.
     * <p/>
     * <p>
     * This method must be called when an activation is complete.
     * This method cannot be called until all required activation
     * methods have been called. No other methods in the move maker can
     * be called after a CardActivator has been received until after its
     * complete method is called. This is really important.
     * </p>
     */
    @Override
    public void complete() {
        getData().stackpush(new RingInteger0(ncards));
        super.complete();
    }

    /**
     * Place a floating card on to a dice disc.
     * <p/>
     * <p>
     * When cards that allow rearrangement are activated, all the cards
     * affected enter a floating state. Cards are then given new
     * locations with this method. The pending activation cannot be
     * completed while there are floating cards.
     * </p>
     *
     * @param card     the card to place
     * @param diceDisc the location for the card to be placed
     */
    @Override
    public void placeCard(Card card, int diceDisc) {
        layCard(card,diceDisc);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
