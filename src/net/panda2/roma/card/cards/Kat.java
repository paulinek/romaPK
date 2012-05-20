package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Kat extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        ge.sayToPlayer(tk, "Miao");

    }

    public Kat(String name, int price, int defense) {
        super("Kat", price, defense);
        this.lives=9;
    }
}
