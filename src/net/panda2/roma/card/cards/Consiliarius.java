package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class Consiliarius extends CharacterCard {

	public Consiliarius(int price, int defense) {
		super("Consiliarius", price, defense);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int ncards = dat.popR0().asInt();
        PlayerState me = ge.getCurrentPlayer(tk);
        RingInteger0 I = new RingInteger0(0);
        for(int i=0; i < me.getNdiscs(); i++) {
            I.set(i);
            PJRomaCard c = me.getDiscCard(I);
            if(c!= null && c instanceof CharacterCard) {
                ge.unlayCard(tk,true,I);
            }
        }
        while(ncards>0) {
            CardLocation cl = dat.popCardLocation();
            me.layCardByName(cl, true);
            ncards--;
        }
    }
}
