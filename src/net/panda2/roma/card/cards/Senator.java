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
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class Senator extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int ncards = dat.popR0().asInt();
        PlayerState me = ge.getCurrentPlayer(tk);
        RingInteger0 I = new RingInteger0(0);
        while(ncards>0) {
            CardLocation cl = dat.popCardLocation();
            me.layCardByName(cl,true);
            ncards--;
        }
    }

    public Senator( int price, int defense) {
        super("Senator", price, defense);
    }
}
