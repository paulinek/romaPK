package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 16/05/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class
        MoneyStash
extends Stash {
    private MoneyStash(int initial, int minAmt) {
        super(initial, minAmt);
    }

    public static MoneyStash createStash(int initial, int minAmt) {
        return new MoneyStash(initial, minAmt);
    }
}
