package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BattleCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public class Centurio extends BattleCard {

	

	public Centurio(int price, int defense) {
		super("Centurio", price, defense);
	}


    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int attackRoll = dat.popR0().asInt();

        // TODO - ask about unused action die

        ge.battleCard(attackRoll, dat.whichDiceDisc, tk);
    }
}
