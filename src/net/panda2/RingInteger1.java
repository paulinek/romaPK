package net.panda2;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RingInteger1 extends RingInteger {
    public RingInteger1( int init, int max) {
        super(max,1);
        set(init);
    }

    public RingInteger1(RingInteger0 ringInteger0) {
        super(ringInteger0.max,1);
        i = ringInteger0.i;
    }

    public RingInteger1(int val) {
        this(val, Integer.MAX_VALUE);
    }

    public RingInteger0 toR0() {
        return new RingInteger0(this);
    }
}
