package net.panda2.roma.game;

import framework.interfaces.activators.CardActivator;
import net.panda2.RingInteger1;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.action.ActivateCardAction;
import net.panda2.roma.action.RomaAction;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PJRomaActivator implements CardActivator {
    //todo - all protected
     boolean deferred;
    PJRomaCard card;
    private boolean bribe;
    RingInteger1 diceVal;
    public RomaAction getAction() {
        return action;
    }

    public void setAction(RomaAction action) {
        this.action = action;
    }

    public ActionData getData() {
        return data;
    }

    public void setData(ActionData data) {
        this.data = data;
    }

    public PlayerState getPlayer() {
        return player;
    }

    public void setPlayer(PlayerState player) {
        this.player = player;
    }

    public PJRomaTestGameState getGst() {
        return gst;
    }

    protected void setGst(PJRomaTestGameState gst) {
        this.gst = gst;
    }

    RomaAction action;
    ActionData data;
    PlayerState player;
    PJRomaTestGameState gst;

    public PJRomaActivator(PJRomaCard c, PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        this.gst=gst;
        this.player=p;
        this.action=a;
        data = new ActionData();
        action.setActionData(data);
        deferred=false;
        card = c;

    }
    public PJRomaActivator(PJRomaCard c, PJRomaTestGameState gst, PlayerState p, RomaAction a,boolean deferred) {
        this(c,gst,p,a);
        this.deferred=deferred;
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
        if(bribe) {
            action.setDiceVal(diceVal.toR0());
            gst.ge.activateBribe(gst.tk, player, (ActivateCardAction) action);
        } else
        if(!deferred) {
            gst.ge.activateCard(gst.tk, player, (ActivateCardAction) action);
        }
    }
    public void completeDeferred() throws RomaException {
        if(card != null) {
            gst.ge.mimicCard(gst.tk,card,action.getDiceVal(), (ActivateCardAction) action);
        }
    }

    protected GameEngine getGE() {
        return gst.ge;
    }
    protected AuthToken getTK() {
        return gst.tk;}

    public void defer() {
        deferred=true;
    }

    public void setBribe(int diceVal) {
        this.diceVal = new RingInteger1(diceVal);
        this.bribe=true;
    }
}

