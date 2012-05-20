package net.panda2;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class RingInteger0 extends RingInteger {
    public RingInteger0(int val, int max) {
        super(max-1);
        this.i=val;
        i%=max;
    }
    public RingInteger0(RingInteger1 r) {
        super(r.max);
        i=r.i;
    }

    public RingInteger0(int val) {
        this( val, Integer.MAX_VALUE);
    }
    public RingInteger1 toR1() {
        return new RingInteger1(this);
    }

}
