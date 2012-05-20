package net.panda2.roma.card.cards;

import framework.interfaces.activators.CenturioActivator;
import net.panda2.roma.action.RomaAction;
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
    public CenturioCardActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(gst, p, a);
        chosen = true;
    }
    boolean chosen;
    int value;
    /**
     * Choose whether to add an action dice to your current attack.
     *
     * @param attackAgain whether to attack again
     */
    @Override
    public void chooseCenturioAddActionDie(boolean attackAgain) {
        if(attackAgain) {
            chosen=false;
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
}
