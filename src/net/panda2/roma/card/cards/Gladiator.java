package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Gladiator extends CharacterCard {
    public Gladiator( int price, int defense) {
        super("Gladiator", price, defense);
    }

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int opponentCardNo = dat.stack.pop();
        ge.unlayCard(tk,false,opponentCardNo);
    }

}
