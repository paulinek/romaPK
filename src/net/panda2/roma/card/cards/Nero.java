package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Nero extends CharacterCard {

	public Nero(int price, int defense) {
		super("Nero", price, defense);

	}

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        RingInteger0 which = dat.popR0();
        // TODO - check it's a building card
        ge.discardEnemyCard(tk, which);
        ge.discardMyCard(tk, dat.whichDiceDisc);
    }
}