package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BuildingCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Basilica extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Basilica(int price, int defense) {
        super("Basilica", price, defense);
    }
}
