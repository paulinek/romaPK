package net.panda2.roma.action;

import net.panda2.RingInteger0;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 3:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActionData {
    RAction action;
    public RingInteger0 whichDiceDisc;
    public Stack<RingInteger0> stack;

    public ActionData(RAction action) {
        this.action = action;
        stack = new Stack<RingInteger0>();
    }

    public ActionData() {
        this(null);
    }
}
