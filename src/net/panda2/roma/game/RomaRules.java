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
    public  int numPlayers = 2;
    //VP
    public  int playerInitVP = 10;
    public  int tableInitVP = 16;

    public  int gameTotalVP = 36;

     // phases

    // victory conditions / endgame
    public  int minVP = 1;

    // how many card slots / dice discs there are
    // these two should always be the same!
    public  int diceSize = 6;
    public  int numDiceDiscs = 6;
    public  int nDice = 3;
    public  int playerInitSest = 10;
    public int playerInitCards=4;
    public  int playerInitTrade=2;
    public int numBribeDiscs=1;
}
