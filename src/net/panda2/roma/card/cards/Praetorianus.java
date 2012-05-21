package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Praetorianus extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        PlayerState me = ge.getCurrentPlayer(tk);
        PlayerState opponent = ge.getNextPlayer(tk);
        RingInteger0 cardRef = dat.popR0();
        opponent.blockCard(tk, cardRef.toR1(), 1);
    }

    public Praetorianus( int price, int defense) {
        super("Praetorianus", price, defense);
    }
}
