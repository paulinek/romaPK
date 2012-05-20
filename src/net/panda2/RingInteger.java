package net.panda2;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RingInteger implements Comparable<RingInteger>{
    int min;
    int max;
    int i;
    public RingInteger(int max){
        setup(0,max);
    }

    public RingInteger(int min, int max) {
        setup(min,max);
    }
    void setup(int min,int max) {
        this.min = min;
        this.max = max;
        i=min;
    }
public
    int inc() {
        i = next();
    return i;
}
    public
    int next() {
       int i = this.i;
        i++;
        if(i > max) {
            i=min;
        }
        return i;
    }
    public int dec() {
        i = prev();
        return i;
    }
    public int prev() {
        int i=this.i;
        i--;
        if(i < min) {

          i = max-1;
        }
        return i;
    }
    public RingInteger set(int i) {
        checkPositionIndex(i, max);
        checkArgument(i>=min);
        this.i=i;
        return this;
    }
    public int get() {
        return i;
    }
    public int asInt() {
        return get();
    }
    public int iterate() throws allDoneException {
        i++;
        if(i==max) {
            throw new allDoneException();
        }
        return i;

    }

    @Override
    public int compareTo(RingInteger ringInteger) {
        if(ringInteger.i < i) {
            return -1;
        }
        if(ringInteger.i > i) {
            return 1;

        }
            return 0;
    }

    public void setMax(int n) {
    max=n;
    i %=max;
    }
}
