package net.panda2.roma.action;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */public enum RAction {
    // modes:
    // no arguments
    // 1 - card in hand, dice disc
    // 2 - action dice
    LAYCARD ("LayCard", 1),
    TAKEMONEY("TakeMoney", 2),
    TAKECARD("TakeCard", 2),
    ACTIVATECARD("ActivateCard", 2),
    ENDTURN("EndTurn", 0);

    // Member to hold the name as a string.
    private String string;
public int mode;
    // Private constructor to construct a card from a string name.
    private RAction (String name, int mode) {
        string = name;
        this.mode = mode;
    }

    // Overwrites the toString function.
    public String toString () {
        return string;
    }

}
