package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.roma.action.RomaAction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayerInteractor {

    public PlayerInteractor() {
    }


    public void say(String s) {
    }

    public abstract List<Integer> selectNCards(String s, ViewableCardCollection hand, int howMany);

    public abstract boolean playerQuestionDiceHappy(ViewableDiceCup dice);

    public abstract RomaAction getLayCardAction(String s, ViewableCardCollection hand, ViewableCardCollection diceDiscCards, boolean b);

    public abstract void printPlayerGameView(PlayerGameView gv);

    public abstract RomaAction getAction(PlayerGameView gv, PlayerState playerState);

    public abstract RingInteger0 chooseTakeCardCard(ViewableCardDeck deck);
}
