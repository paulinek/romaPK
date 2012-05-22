package net.panda2.roma.card.cards;


import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Haruspex extends CharacterCard {


	public Haruspex(int price, int defense) {
		super("Haruspex", price, defense);
	}



    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {

        RingInteger0 cardIndex = dat.popR0();
        ge.takeDeckCard(tk,ge.getCurrentPlayer(tk), cardIndex);
    }
}