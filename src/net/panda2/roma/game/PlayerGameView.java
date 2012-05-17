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

    public int getMyMoney() {
        return myMoney;
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
    setup(stashVPs,myVPs,myMoney,opponent,mydiscs,mydice,myhand);
    }
    void setup(int stashVPs, int myVPs, int myMoney, List<CardView> opponent, List<CardView> mydiscs, List<DiceView> mydice, List<CardView> myhand) {

        this.stashVPs = stashVPs;
        this.myVPs = myVPs;
        this.myMoney=myMoney;
        this.opponent = opponent;
        this.mydiscs = mydiscs;
        this.mydice = mydice;
        this.myhand = myhand;
    }
    PlayerGameView(GameState gs) {
        PlayerState p
               = gs.currentPlayer();
        List<CardView> opponentHand = gs.getNextPlayer().getDiscView();
        List<CardView> myTab = p.getDiscView();
        List<DiceView> myDice = p.dice.getDiceView();
        List<CardView> myHand = p.hand.getCardView();
        setup(gs.tabletopVPStockpile.getAmount(), p.vp.getAmount(),p.money.getAmount(), opponentHand, myTab, myDice, myHand);
    }

    int stashVPs;
     int myVPs;
     int myMoney;
     List<CardView> opponent;
     List<CardView> mydiscs;
     List<DiceView> mydice;
     List<CardView> myhand;

}
