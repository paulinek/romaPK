package net.panda2.roma.game;

import net.panda2.roma.card.*;
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
    Stash vp;
    GameEngine ge;
    ViewableCardDeck hand;
    int money;
    ViewableDiceCup dice;
    ViewableTableau diceDiscCards;

    public PlayerState(RomaRules rules, GameEngine ge) {
        vp = Stash.createStash(rules.playerInitVP, rules.minVP);
        this.ge = ge;
        dice = new ViewableDiceCup(rules.nDice);
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
}
