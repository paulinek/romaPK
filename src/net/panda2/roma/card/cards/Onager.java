package net.panda2.roma.card.cards;

import net.panda2.RingInteger0;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BuildingCard;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class Onager extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        PlayerState opponent = ge.getNextPlayer(tk);
        int attackRoll = dat.popR0().asInt();
        RingInteger0 which = dat.popR0();
        PJRomaCard c = opponent.getDiscCard(which);
        if(c instanceof BuildingCard) {
            ge.battleCard(tk, attackRoll,which);

        }
    }

    public Onager(int price, int defense) {
        super("Onager", price, defense);
    }
}
