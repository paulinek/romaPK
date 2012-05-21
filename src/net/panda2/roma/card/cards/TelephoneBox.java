package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BuildingCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 22/05/12
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class TelephoneBox extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        throw new RomaException("Telephone box not activatable");
    }

    public TelephoneBox(int price, int defense) {
        super("TelephoneBOx", price, defense);
    }
}
