package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaException;

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
    ViewableTableau diceDiscCards;
    String playerName;
     boolean grimReaperActive;
    int diceSize;

    public String getPlayerName() {
        return playerName;
    }

    public PlayerState(RomaRules rules, GameEngine ge, String playerName, StashFactory<VPStash> VPrepository, StashFactory<MoneyStash> MoneyRepository) {
        this.ge = ge;
        this.playerName = playerName;
        vp = VPrepository.make(rules.playerInitVP, rules.minVP);
        money = MoneyRepository.make(rules.playerInitSest);
        diceSize = rules.diceSize;
        dice = new ViewableDiceCup(rules.nDice, diceSize);
        diceDiscCards = new ViewableTableau(rules.numDiceDiscs + rules.numBribeDiscs);
        hand = new ViewableCardDeck();
    }


    public Stash getVP(AuthToken tk) throws RomaException {
        ge.authenticateOrDie(tk);
        return vp;

    }
    public ViewableTableau getTableau(AuthToken tk) {
        if(ge.authenticateToken(tk)) return diceDiscCards;
        return null;
    }
    List<CardView> getDiscView() {
        return diceDiscCards.getCardView();
    }

    int getIndexOfCard(PJRomaCard c) {
        return diceDiscCards.getIndexOfCard(c);
    }

    public void receiveCard(AuthToken tk, PJRomaCard c) {
    if(ge.authenticateToken(tk))
        hand.addCard(c);
    }

    public void layCard(RingInteger0 cardNo, RingInteger0 discNo) {

        if(diceDiscCards.get(discNo) != null) {
            diceDiscCards.discard(discNo, ge.gs.discard);
        }
   hand.layCard(cardNo, diceDiscCards, discNo);
    }

    public void useupDice(RingInteger0 diceNo) {
            dice.useup(diceNo);
    }

    public RingInteger1 getDiceValue(RingInteger0 dice) {
        return new RingInteger1(diceSize, this.dice.getNth(dice));
    }

    public boolean hasGrimReaper() {


        return diceDiscCards.howManyOfThese("GrimReaper") > 0;
    }

    public int defenseBonus() {
        int bonus = 0;
        // todo:
        // search for turris and return 1
        bonus += diceDiscCards.howManyOfThese("Turris");
    return bonus;
    }
}
