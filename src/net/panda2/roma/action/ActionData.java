package net.panda2.roma.action;

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
    public int whichDiceDisc;
    public Stack<Integer> stack;

    public ActionData(RAction action) {
        this.action = action;
        stack = new Stack<Integer>();
    }

    public ActionData() {
        this(null);
    }
}
