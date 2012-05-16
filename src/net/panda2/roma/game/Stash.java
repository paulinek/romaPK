package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public  class Stash implements Cloneable{
    int amount;
    int minAmt;


    Stash(int initial, int minAmt){
    setup(initial,minAmt);
    }
    void setup(int initial, int minAmt) {
        amount=initial;
        this.minAmt =minAmt;

    }

    public Stash duplicate(int initial, int minAmt) {
        Stash s = null;
        try {
            s = (Stash) this.clone();
            s.setup(initial, minAmt);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    return s;
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


    public void giveAway(Stash to) {
        try {
            transferAway(to, amount);
        } catch (RomaGameEndException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

