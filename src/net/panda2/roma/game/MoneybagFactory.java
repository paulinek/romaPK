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
This is a Factory class for VictoryPoints

This class exists so that we can assert that VPs have not appeared out of thin air
See Factory design pattern in Gamma
 */
public class MoneybagFactory {
    ArrayList<MoneyBag> allocatedObjects;
    int maxVPs;
    int allocatedVPs;
    public MoneybagFactory(int maxVPs) {
        allocatedObjects = new ArrayList<MoneyBag>();
        this.maxVPs = maxVPs;
        allocatedVPs=0;
    }
    public MoneyBag makeVP(int initialAmt) {
       return makeVP(initialAmt, -1);
    }
    public MoneyBag makeVP(int initialAmt, int minAmt) {

        MoneyBag vp = new MoneyBag(initialAmt, minAmt);
        allocatedVPs+= initialAmt;
        allocatedObjects.add(vp);
        return vp;
    }

    public boolean checkForCheaters() throws RomaCheatingException {
        if(allocatedVPs > maxVPs) {
            return true;
        }
        int VPs =0;
        for(MoneyBag v : allocatedObjects) {
            VPs += v.pointsTotal;

        }
        if(VPs != allocatedVPs)
        {
        return true;
        }

        return false;
    }

}
