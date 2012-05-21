package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Essedum extends CharacterCard {


	public Essedum(int price, int defense) {
		super("Essedum", price, defense);
	}


    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        ge.knockOffDefense(tk, 2);
    }
}
