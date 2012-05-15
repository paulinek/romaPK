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
        int dice = dat.stack.pop().intValue();
        int amt = dat.stack.pop().intValue();
        if(amt < 0)
            amt=-1;
        else
            amt=1;
        ge.fiddleDice(dice,amt,tk);
                //To change body of implemented methods use File | Settings | File Templates.
    }
}
