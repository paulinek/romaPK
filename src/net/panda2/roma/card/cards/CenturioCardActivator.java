package net.panda2.roma.card.cards;

import framework.interfaces.activators.CenturioActivator;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.PJRomaTestGameState;
import net.panda2.roma.game.PlayerState;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenturioCardActivator extends AttackCardActivator implements CenturioActivator {
    public CenturioCardActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(card, gst, p, a);
        chosen = true;
        hasDie=false;
    }
    boolean chosen;
    int value;
    boolean hasDie;
    /**
     * Choose whether to add an action dice to your current attack.
     *
     * @param attackAgain whether to attack again
     */
    @Override
    public void chooseCenturioAddActionDie(boolean attackAgain) {
        if(attackAgain) {
            chosen=false;

            hasDie=true;
        }
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
        value=actionDiceValue;
        chosen = true;
        //To change body of implemented methods use File | Settings | File Templates.
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
        if(hasDie){
            getData().stackpush(new RingInteger1(value).toR0());
            getData().stackpush(new RingInteger0(1));
        } else {
            getData().stackpush(new RingInteger0(0));
        }
        super.complete();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
