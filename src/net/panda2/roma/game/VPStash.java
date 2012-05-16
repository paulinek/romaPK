package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 16/05/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class VPStash extends Stash{

    private VPStash(int initial, int minAmt) {
        super(initial, minAmt);
    }

    public static VPStash createStash(int initial, int minAmt) {
        return new VPStash(initial, minAmt);
    }
}
