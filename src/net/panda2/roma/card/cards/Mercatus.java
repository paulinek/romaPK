package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BuildingCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Mercatus extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
      PlayerState p = ge.getCurrentPlayer(tk);
      PlayerState opponent = ge.getNextPlayer(tk);
      int numVPs=opponent.countForums();
      opponent.takeVPs(tk,p,numVPs);
    }

    public Mercatus( int price, int defense) {
        super("Mercatus", price, defense);
    }
}
