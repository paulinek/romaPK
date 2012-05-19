package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.RomaGameState;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class TribunusPlebis extends CharacterCard {

	public TribunusPlebis(int price, int defense) {
		super("TribunusPlebis", price, defense);
	}

    @Override
	public void activate(GameEngine ge, AuthToken tk,ActionData dat) throws RomaException {
        RomaGameState gs = ge.getGameState(tk);
        PlayerState me, opponent;

        me = gs.currentPlayer(tk);
        opponent = gs.getNextPlayer(tk);
        opponent.getVP(tk).transferAway(me.getVP(tk), 1, tk);

	}


}
