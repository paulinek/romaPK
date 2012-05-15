package net.panda2.roma.game;

import net.panda2.roma.card.CardView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
/* this class encapsulates a playerAgent's view of the game.

 */
public class PlayerGameView {
    public int getMyVPs() {
        return myVPs;
    }

    public List<CardView> getOpponent() {
        return opponent;
    }

    public List<CardView> getMydiscs() {
        return mydiscs;
    }

    public List<DiceView> getMydice() {
        return mydice;
    }

    public List<CardView> getMyhand() {
        return myhand;
    }

    public int getStashVPs() {

        return stashVPs;
    }
public

    PlayerGameView(int stashVPs, int myVPs, List<CardView> opponent,
                   List<CardView> mydiscs, List<DiceView> mydice, List<CardView> myhand) {
    setup(stashVPs,myVPs,opponent,mydiscs,mydice,myhand);
    }
    void setup(int stashVPs, int myVPs, List<CardView> opponent, List<CardView> mydiscs, List<DiceView> mydice, List<CardView> myhand) {

        this.stashVPs = stashVPs;
        this.myVPs = myVPs;
        this.opponent = opponent;
        this.mydiscs = mydiscs;
        this.mydice = mydice;
        this.myhand = myhand;
    }
    PlayerGameView(GameState gs) {
        PlayerState p
               = gs.currentPlayer();

        setup(gs.tabletopVPStockpile.getAmount(),
                p.vp.getAmount(), gs.getNextPlayer().getDiscView(), p.getDiscView(), p.dice.getDiceView(),p.hand.getCardView());
    }
    int stashVPs;
     int myVPs;
     List<CardView> opponent;
     List<CardView> mydiscs;
     List<DiceView> mydice;
     List<CardView> myhand;

}
