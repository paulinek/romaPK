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
        super(max);
        if(init == 0) {
             i = max-1;

        } else {
        i = init -1;
        }
    }

    public RingInteger1(RingInteger0 ringInteger0) {
        super(ringInteger0.max);
        i = ringInteger0.i;
    }

    public RingInteger1(int val) {
        this(val, Integer.MAX_VALUE);
    }

    public RingInteger0 toR0() {
        return new RingInteger0(this);
    }
    @Override
    public int get() {
        return super.get()+1;    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int inc() {
        return super.inc()+1;    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int next() {
        return super.next()+1;    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int dec() {
        return super.dec() + 1;

    }

    @Override
    public int prev() {
        return super.prev() + 1;    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public RingInteger set(int i) {
        return super.set((i-1+max)%max);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
