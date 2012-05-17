package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.CharacterCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.Stash;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/05/12
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FredHilmer extends CharacterCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        PlayerState me = ge.getCurrentPlayer(tk);
        PlayerState opponent = ge.getNextPlayer(tk);
        Stash theirs = opponent.getVP(tk);
        Stash mine = me.getVP(tk);

        theirs.transferAway(mine, theirs.getAmount(),tk);
    }

    public FredHilmer(int price, int defense) {
        super("FredHilmer", price, defense);
        this.dataMode = 0;
    }
}
