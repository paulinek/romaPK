package net.panda2.game.dice;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
/*
DiceCollection:
Represents a "Dice Roll" like in D&D:
eg 3d6+3
defaults to 1d6+0

 */
public class DiceCollection {
    int ndice;
    int sides;
    Dice d;
    int offset;
    void makeDiceCollection(int ndice, int sides, int offset) {
        d = new Dice(sides);
        this.ndice = ndice;
        this.offset = offset;
    }
    public DiceCollection() {
        makeDiceCollection(1,6,0);
    }
    public DiceCollection(int ndice, int sides, int offset) {
        makeDiceCollection(ndice,sides,offset);
    }

    public int roll() {
        int total=0;
        int i;
        for(i=0; i < ndice; i++) {
            total += d.roll();

        }
        total += offset;
        return total;
    }
}

