package net.panda2.roma.card;

import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameState;
import net.panda2.roma.game.PlayerState;
import net.panda2.roma.game.RomaException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 1:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Legat extends CharacterCard {
    public Legat()  {
        this.price=5;
        this.defence=2;
        this.dice=1;
    }

    public void doAction(PlayerState p, GameState gs, AuthToken tk) throws RomaException{
// the legat card - a player gets 1 vp from the stockpile for every dice disc not occupied by the opponent
// TODO:  count dice disks
//
     gs.getVP(tk).transferVPAway(p.getVP(tk),1,tk);
    }
}
