package net.panda2.roma.card.cards;

import framework.interfaces.activators.*;
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
 * Date: 20/05/12
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AttackCardActivator extends PJRomaActivator implements
        LegionariusActivator, PraetorianusActivator, SicariusActivator,
        TribunusPlebisActivator, GladiatorActivator, NeroActivator,
        OnagerActivator, VelitesActivator,
        Attacker, CardActivator, Targeted {
    public AttackCardActivator(PJRomaCard card, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        super(card, gst,p,a);
    }
    RingInteger0 attackRoll;


    /**
     * Give the result of an attack die roll.
     * <p/>

     * <p>
     * Only valid if the pending activation requires an attack dice
     * roll.
     * </p>
     *
     * @param roll the outcome of the attack dice roll
     */
    @Override
    public void giveAttackDieRoll(int roll) {
        //To change body of implemented methods use File | Settings | File Templates.
        attackRoll = new RingInteger0(roll);
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
        if(attackRoll != null)
             getData().stackpush(attackRoll);
        super.complete();
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * The user chooses a dice disc.
     * <p/>
     * <p>
     * Only valid if the pending activation requires a dice disc from
     * the user at the point this method is called.
     * </p>
     *
     * @param diceDisc dice disc to use, by dice value
     */
    @Override
    public void chooseDiceDisc(int diceDisc) {
        getData().stackpush( new RingInteger1(diceDisc).toR0());

        //To change body of implemented methods use File | Settings | File Templates.
    }

}
