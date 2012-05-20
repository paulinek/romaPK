package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Consul extends CharacterCard {

	public Consul(int price, int defense) {
		super("Consul", price, defense);

	}


    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
         int amt = dat.popR0().asInt()-1;
        int dice = dat.popR0().asInt();


        ge.fiddleDice(dice,amt,tk);
                //To change body of implemented methods use File | Settings | File Templates.
    }
}
