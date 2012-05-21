package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 21/05/12
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrimReaper extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
    // does nothing
        }

    public GrimReaper( int price, int defense) {
        super("GrimReaper", price, defense);
    }
}
