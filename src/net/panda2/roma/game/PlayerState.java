package net.panda2.roma.game;

import net.panda2.game.card.CardDeck;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerState {
    VictoryPoints vp;
    GameEngine ge;
    CardDeck hand;
    int money;
    public PlayerState(RomaRules rules, GameEngine ge) {
        vp = new VictoryPoints(rules.playerInitVP, rules.minVP);
        this.ge = ge;

    }


    public VictoryPoints getVP(AuthToken tk) throws RomaException {
        if(ge.authenticateToken(tk)) {
            return vp;
        } else {
            throw new RomaUnAuthException();
        }
    }
}
