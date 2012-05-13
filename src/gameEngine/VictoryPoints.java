package gameEngine;

import Roma.AuthToken;
import Roma.RomaException;
import Roma.RomaUnAuthException;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class VictoryPoints {
    int pointsTotal;
    int minPoints;

    public  VictoryPoints(int pointsTotal, int minVP){
        pointsTotal=pointsTotal;
        minPoints=minVP;

    }

public int getPointsTotal(){
    return pointsTotal;
}
// secret transfer method which moves points around without changing Total points in the game
public boolean transferVPAway (VictoryPoints to, int amt, AuthToken tk) throws RomaException {
    // add amt to the total at destination object
    // take away amt from this total

    boolean gameEndedQ = false;

    to.pointsTotal+=amt;
    pointsTotal-=amt;

    if (pointsTotal<minPoints){
        gameEndedQ=true;
    }

    return gameEndedQ;
}






}

