package net.panda2.roma.game;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class RomaRules {
    // initialisation
    // Number of Players
    public final int numPlayers = 2;
    //VP
    public final int playerInitVP = 10;
    public final int tableInitVP = 16;

    public final int gameTotalVP = 36;

    // cards
    public final int LegatCount = 2;
    public final int  LegatDefense = 5;
    public final int LegatCost = 2;
    // phases

    // victory conditions / endgame
    public final int minVP = 0;

    // how many card slots / dice discs there are
    // these two should always be the same!
    public final int diceSize = 6;
    public final int diceDiscs = 6;
    public final int nDice = 3;
}
