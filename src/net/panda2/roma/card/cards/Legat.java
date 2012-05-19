package net.panda2.roma.card.cards;

import net.panda2.game.card.Tableau;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.RomaGameState;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class Legat extends PJRomaCard {


	public Legat(int price, int defense){
        super("Legat", price, defense, true);
	}


    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        RomaGameState gs = ge.getGameState(tk);
        PlayerState opponent, me;

        opponent = gs.getNextPlayer(tk);
        me = gs.currentPlayer(tk);

        Tableau<PJRomaCard> discs = opponent.getTableau(tk);

        opponent.getVP(tk).transferAway(me.getVP(tk), discs.numEmpty(), tk);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
