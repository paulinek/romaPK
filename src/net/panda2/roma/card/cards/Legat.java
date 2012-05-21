package net.panda2.roma.card.cards;

import net.panda2.game.card.Tableau;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

public class Legat extends CharacterCard {


	public Legat(int price, int defense){

        super("Legat", price, defense);
	}


    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        PlayerState opponent, me;

        opponent = ge.getNextPlayer(tk);
        me = ge.getCurrentPlayer(tk);

        Tableau<PJRomaCard> discs = opponent.getTableau(tk);

        ge.takeVPs(tk, me, discs.numEmpty());
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
