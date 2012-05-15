package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stash {
    int amount;
    int minAmt;

    private Stash(int initial, int minAmt){
        amount=initial;
        this.minAmt =minAmt;

    }

    static Stash createStash(int initial, int minAmt) {
        return new Stash(initial, minAmt);
    }

    public int getAmount(){
    return amount;
}
// secret transfer method which moves points around without changing Total points in the game
public boolean transferAway(Stash to, int amt, AuthToken tk) throws RomaGameEndException {

    return transferAway(to, amt);
}

    boolean transferAway(Stash to, int amt) throws RomaGameEndException {


    // add amt to the total at destination object
    // take away amt from this total

    boolean gameEndedQ = false;

    to.amount +=amt;
    amount -=amt;


    if (amount < minAmt){
        throw new RomaGameEndException();
    }

    return gameEndedQ;
}






}

