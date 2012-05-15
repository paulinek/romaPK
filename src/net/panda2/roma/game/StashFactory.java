package net.panda2.roma.game;

import net.panda2.roma.game.exception.RomaCheatingException;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
/*
This is a Factory class for Stash

This class exists so that we can assert that VPs have not appeared out of thin air
See Factory design pattern in Gamma
 */
public class StashFactory <S extends Stash>{
    ArrayList<S> stashes;
    int maximum;
    int allocatedAmt;
    public StashFactory(int maximum) {
        stashes = new ArrayList<S>();
        this.maximum = maximum;
        allocatedAmt =0;
    }
    public S make(int initialAmt) {
       return make(initialAmt, -1);
    }
    public S make(int initialAmt, int minAmt) {

        S s;
        s = (S) S.createStash(initialAmt, minAmt);
        allocatedAmt += initialAmt;
        stashes.add(s);
        return s;
    }

    public boolean checkForCheaters() throws RomaCheatingException {
        if(allocatedAmt > maximum) {
            return true;
        }
        int inCirculation =0;
        for(S v : stashes) {
            inCirculation += v.amount;

        }
        if(inCirculation != allocatedAmt)
        {
        return true;
        }

        return false;
    }

}
