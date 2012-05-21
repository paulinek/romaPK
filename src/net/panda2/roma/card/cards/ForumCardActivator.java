package net.panda2.roma.card.cards;

import framework.interfaces.activators.ForumActivator;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.PJRomaActivator;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForumCardActivator extends PJRomaActivator implements ForumActivator {
    boolean templumActive;
    private int templumDiceVal;
    int forumDiceVal;


    public ForumCardActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a, boolean templumActive) {
        super(card, gst, p, a);
        this.templumActive = templumActive;
    }
    public ForumCardActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a) {

       this(card, gst,p,a,false);
    }

    /**
     * Choose whether to activate a Templum with your forum activation
     *
     * @param activate true if the user wishes to activate a Templum.
     */
    @Override
    public void chooseActivateTemplum(boolean activate) {
templumActive = true;
    }

    /**
     * Choose the dice to use with the Templum.
     * <p/>
     * This should only be called if chooseActivateTemplum has previously
     * been called with true as a parameter.
     *
     * @param diceValue the dice to use
     */
    @Override
    public void chooseActivateTemplum(int diceValue) {
        templumDiceVal=diceValue;
        getPlayer().useupDiceByVal(diceValue);
    }

    /**
     * The user chooses an action dice.
     * <p/>
     * <p>
     * An action dice of the given value is used. A dice of that value
     * must exist in the user's current unused action dice. The same
     * dice cannot be used multiple times on the same turn. The same
     * value can be used if the user has multiple dice of that value.
     * </p>
     * <p/>
     * <p>
     * Action dice are always referred to by their value, rather than
     * their index, because action dice do not have either an implicit
     * or explicit ordering.
     * </p>
     *
     * @param actionDiceValue the value of the dice to use
     */
    @Override
    public void chooseActionDice(int actionDiceValue) {
        checkArgument(getPlayer().findDice(new RingInteger1(actionDiceValue))!= null);
        forumDiceVal = actionDiceValue;
        getPlayer().useupDiceByVal(actionDiceValue);
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
        getData().stackpush(new RingInteger0(forumDiceVal + templumDiceVal));
        super.complete();
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
