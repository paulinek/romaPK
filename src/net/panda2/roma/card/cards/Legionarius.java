package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Legionarius extends CharacterCard {

	public Legionarius(int price, int defense) {
		super("Legionarius", price, defense);
	}

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int attackRoll = dat.stack.pop();

        ge.battleCard(attackRoll, dat.whichDiceDisc, tk);
    }
}
