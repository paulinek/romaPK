package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Sicarius extends CharacterCard {

	public Sicarius( int price, int defense) {
		super("Sicarius", price, defense);

	}

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        RingInteger0
                which = dat.stack.pop();
        // TODO - check it's a characterCard
        ge.destroyCard(which,tk);
        ge.destroyCard(dat.whichDiceDisc, true, tk);
    }
}