package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
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
        int attackRoll = 0;
        attackRoll = dat.popR0().asInt();
        int ndie = dat.popR0().asInt();
        while(ndie>0) {
            RingInteger0 diceR0= dat.popR0();
            RingInteger1 diceVal = diceR0.toR1();
            ge.getCurrentPlayer(tk).useupDiceByVal(diceVal);
            attackRoll+= diceVal.asInt();
            ndie--;
        }


        ge.battleCard(tk, attackRoll, dat.whichDiceDisc);
    }
}
