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
 * Time: 1:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Machina extends BuildingCard {
    @Override
    public void activate(GameEngine ge, AuthToken tk, ActionData dat) throws RomaException {
        int ncards = dat.popR0().asInt();
        PlayerState me = ge.getCurrentPlayer(tk);
        RingInteger0 I = new RingInteger0(0);
        for(int i=0; i < me.getNdiscs(); i++) {
            I.set(i);
            PJRomaCard c = me.getDiscCard(I);
            if(c!= null && c instanceof BuildingCard) {
                ge.unlayCard(tk,true,I);
            }
        }
        while(ncards>0) {
            CardLocation cl = dat.popCardLocation();
            me.layCardByName(cl);
            ncards--;
        }
         //To change body of implemented methods use File | Settings | File Templates.
    }

    public Machina( int price, int defense) {
        super("Machina", price, defense);
    }
}
