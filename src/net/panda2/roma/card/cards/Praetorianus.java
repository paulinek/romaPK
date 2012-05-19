package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Praetorianus extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Praetorianus( int price, int defense) {
        super("Praetorianus", price, defense);
    }
}
