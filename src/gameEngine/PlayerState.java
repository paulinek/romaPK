package gameEngine;

import Roma.AuthToken;
import Roma.RomaException;
import Roma.RomaUnAuthException;

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
