package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Velites extends CharacterCard {

	public Velites(int price, int defense) {
		super("Velites", price, defense);
	}

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int which = dat.stack.pop();
        int attackRoll = ge.rollBattle();

        ge.battleCard(attackRoll,which, tk);

    }
}
