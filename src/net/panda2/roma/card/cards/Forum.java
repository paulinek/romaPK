package net.panda2.roma.card.cards;

import net.panda2.roma.action.ActionData;
import net.panda2.roma.card.BuildingCard;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 19/05/12
 * Time: 1:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Forum extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int numVPs = dat.stack.pop().asInt();
        int nbasilicas;
        dat.whichDiceDisc.setMax(ge.getCurrentPlayer(tk).getNdiscs());
       nbasilicas = ge.getCurrentPlayer(tk).hasAdjacentCard(dat.whichDiceDisc,"Basilica");

            numVPs+=2*nbasilicas;

        ge.takeVPs(tk,ge.getCurrentPlayer(tk), numVPs);
    }

    public Forum( int price, int defense) {
        super("Forum", price, defense);
    }
}
