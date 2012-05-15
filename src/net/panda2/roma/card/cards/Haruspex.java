package net.panda2.roma.card.cards;


import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.GameState;
import net.panda2.roma.game.exception.RomaException;

public class Haruspex extends CharacterCard {


	public Haruspex(int price, int defense) {
		super("Haruspex", price, defense);
	}



    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
    // we implement this as give the player a random card
       GameState gs = ge.getGameState(tk);
        PJRomaCard c = gs.dealRandomCard(tk);
        ge.getCurrentPlayer(tk).receiveCard(tk, c);
    }
}