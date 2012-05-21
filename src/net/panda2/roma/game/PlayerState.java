package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.card.cards.CardLocation;
import net.panda2.roma.game.exception.RomaException;
import net.panda2.roma.game.exception.RomaGameEndException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerState {
    VPStash vp;
    GameEngine ge;
    ViewableCardDeck hand;
    MoneyStash money;
    ViewableDiceCup dice;
    ViewableTableau fields;
    String playerName;
     boolean grimReaperActive;
    int diceSize;

    public String getPlayerName() {
        return playerName;
    }

    public PlayerState(RomaRules rules, GameEngine ge, RomaGameState gs, String playerName) {
        this.ge = ge;
        this.playerName = playerName;
        vp = gs.allocate("VP",rules.playerInitVP, rules.minVP);
        money = gs.allocate("money", rules.playerInitSest);
        diceSize = rules.diceSize;
        dice = new ViewableDiceCup(rules.nDice, diceSize);
        fields = new ViewableTableau(rules.numDiceDiscs + rules.numBribeDiscs);
        hand = new ViewableCardDeck();
    }


    public Stash getVP(AuthToken tk) throws RomaException {
        ge.authenticateOrDie(tk);
        return vp;

    }
    public ViewableTableau getTableau(AuthToken tk) {
        if(ge.authenticateToken(tk)) return fields;
        return null;
    }
    List<CardView> getDiscView() {
        return fields.getCardView();
    }

    int getIndexOfCard(PJRomaCard c) {
        return fields.getIndexOfCard(c);
    }

    public void receiveCard(AuthToken tk, PJRomaCard c) {
    if(ge.authenticateToken(tk))
        hand.addCard(c);
    }

    public void layCard(RingInteger0 cardNo, RingInteger0 discNo) {

        if(fields.get(discNo) != null) {
            fields.discard(discNo, ge.gs.discard);
        }
   hand.layCard(cardNo, fields, discNo);
    }

    public void useupDice(RingInteger0 diceNo) {
            dice.useup(diceNo);
    }

    public RingInteger1 getDiceValue(RingInteger0 dice) {
        return new RingInteger1(this.dice.getNth(dice));
    }

    public boolean hasGrimReaper() {


        return fields.howManyOfThese("GrimReaper") > 0;
    }

    public int defenseBonus() {
        int bonus = 0;
        // todo:
        // search for turris and return 1
        bonus += fields.howManyOfThese("Turris");
    return bonus;
    }

     public RingInteger0 findDice(RingInteger1 diceToUse) {
        return dice.getDiceNoFromValue(diceToUse);
    }

    public int hasAdjacentCard(RingInteger0 disc, String type) {
        List<CardView> cv = fields.getCardView();
        int n = 0;
        if(cv.get(disc.prev()).equals(type) ){
            n++;
        }
        if(cv.get(disc.next()).equals(type)) {
            n++;
        }
        return n;
    }

    public int getNdiscs() {
        return fields.getSize();
    }

    public int[] getDiceArray() {
        int ndice=this.dice.getNDice();
        ArrayList<Integer> newDice = new ArrayList<Integer>();
        RingInteger0 I = new RingInteger0(0);
        for(int i=0; i<ndice;i++) {
            I.set(i);
            if(!this.dice.isNthUsed(I)) {
                newDice.add(new Integer(this.dice.getNth(I)));
            }
        }
        int []dice = new int[newDice.size()];
        for(int i=0;i<newDice.size();i++){
            dice[i] = newDice.get(i).intValue();
        }
        return dice;
    }

    public int countForums() {
        return fields.howManyOfThese("Forum");
    }

    public void takeVPs(AuthToken tk, PlayerState p, int numVPs) throws RomaGameEndException {
        if(ge.authenticateToken(tk)) {
                vp.transferAway(p.vp, numVPs);
        }
    }

    public void useupDiceByVal(RingInteger1 diceValue) {

        dice.useupByVal(diceValue);
    }

    public void takeMoney(AuthToken tk, PlayerState p, int amt) throws RomaGameEndException {
            money.transferAway(p.money, amt);
    }

    public PJRomaCard getDiscCard(RingInteger0 i) {
        return fields.get(i);
    }

    public void layCardByName(CardLocation cl, boolean b) {
        RingInteger0 cardNo = hand.findCard(cl.name);
        layCard(cardNo, cl.location.toR0());
    }

    public void useupDiceByVal(int diceValue) {
        useupDiceByVal(new RingInteger1(diceValue));
    }

    public void blockCard(AuthToken tk, RingInteger1 discVal, int turns) {
        if(ge.authenticateToken(tk)) {
            // a turn is actually a single player's turn
            // so we multiply by the number of players

            fields.setBlockedUntil(discVal, ge.getTurnNo() + turns * ge.numPlayers());
        }
    }
}
