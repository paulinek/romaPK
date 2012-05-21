package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class Architectus extends CharacterCard {

	public Architectus(int price, int defense) {
		super("Architectus", price, defense);
		// TODO Auto-generated constructor stub
	}



    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        // at the start, the stack contains the number of cards, and a bunch of cardLocations
        int ncards = dat.popR0().asInt();
        PlayerState me = ge.getCurrentPlayer(tk);
        RingInteger0 I = new RingInteger0(0);
        while(ncards>0) {
            CardLocation cl = dat.popCardLocation();
            me.layCardByName(cl, true);
            ncards--;
        }
    }
}
