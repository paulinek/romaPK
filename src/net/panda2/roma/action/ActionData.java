package net.panda2.roma.action;

import framework.interfaces.activators.CardActivator;
import net.panda2.RingInteger0;
import net.panda2.roma.card.cards.CardLocation;

import java.util.Stack;

import static com.google.common.base.Preconditions.checkArgument;

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
     Stack stack;

    public ActionData(RAction action) {
        this.action = action;
        stack = new Stack<RingInteger0>();
    }

    public ActionData() {
        this(null);
    }

    public RingInteger0 popR0() {
        return (RingInteger0) stack.pop();
    }

    public void stackpush(Object o) {
        checkArgument(o instanceof RingInteger0 || o instanceof CardLocation || o instanceof CardActivator);
        stack.push(o);
    }

    public CardLocation popCardLocation() {
        return (CardLocation) stack.pop();
    }

    public CardActivator popCardActivator() {
        return (CardActivator) stack.pop();
    }
}
