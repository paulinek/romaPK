package net.panda2;
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
    int returnOffset;
    public RingInteger(int max,int returnOffset){
        this.max=max;
        this.returnOffset=returnOffset;
    }


public int inc() {
    i++;
    i%=max;
    return i+returnOffset;
}
    public
    int next() {
        return (i+1)%max + returnOffset;


    }
    int calcPrev() {
        int i=this.i;
        i--;
        if(i < 0) {

            i = max-1;
        }
        return i;
    }
    public int dec() {
        i = calcPrev();
        return i+returnOffset;
    }
    public int prev() {
        return calcPrev()+returnOffset;
    }
    public RingInteger set(int i) {
        i-=returnOffset;
        if(i<0) {
            i+= max;
        }
        this.i=i%max;
        return this;
    }
    public int get() {
        return i+returnOffset;
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
