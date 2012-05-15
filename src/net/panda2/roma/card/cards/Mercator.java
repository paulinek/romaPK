package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class Mercator extends CharacterCard {
	
	public Mercator( int price, int defense) {
		super("Mercator", price, defense);
	}
/*

	@Override
	public void activate() {
		System.out.println("How many victory points would you like to buy?");
		int ownerMoney = getOwner().assets().sestertii();
		int enemyPoints = getOwner().assets().victoryPoints();
		int maxRange = 0;
		if (ownerMoney/2 > enemyPoints) {
			maxRange = enemyPoints;
		} else {
			maxRange = ownerMoney/2;
		}
		if (maxRange > 3) {
			//part of spec
			maxRange = 3;
		}
		System.out.printf("You can buy a max of %d points", maxRange);
		int input = PlayerInput.numbers(0, maxRange);
		
		getOwner().assets().incVictoryPoints(input);
		getOwner().enemyPlayer().assets().decVictoryPoints(input);
		
		getOwner().assets().decSestertii(2*input);
		getOwner().enemyPlayer().assets().incSestertii(2*input);

	}
*/

    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int nVictoryPoints = dat.stack.pop();
        PlayerState me = ge.getCurrentPlayer(tk);
    }
}
