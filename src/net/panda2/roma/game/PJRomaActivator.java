package net.panda2.roma.game;

import framework.interfaces.activators.CardActivator;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.action.ActivateCardAction;
import net.panda2.roma.action.RomaAction;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PJRomaActivator implements CardActivator {
    //todo - all protected
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

    public PJRomaActivator(PJRomaTestGameState gst, PlayerState p, RomaAction a) {
        this.gst=gst;
        this.player=p;
        this.action=a;
        data = new ActionData();
        action.setActionData(data);

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
        gst.ge.activateCard(player, (ActivateCardAction) action, data, gst.tk);

    }

    protected GameEngine getGE() {
        return gst.ge;
    }
    protected AuthToken getTK() {
        return gst.tk;}
}

