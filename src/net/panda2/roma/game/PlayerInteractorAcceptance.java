package net.panda2.roma.game;

import net.panda2.RingInteger0;
import net.panda2.allDoneException;
import net.panda2.roma.action.LayCardAction;
import net.panda2.roma.action.RomaAction;

import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 5:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInteractorAcceptance extends PlayerInteractor {
        public Stack interactionData;  // unsafe but how else?

    public PlayerInteractorAcceptance() {
        this(new Stack());
    }

    public PlayerInteractorAcceptance(Stack interactionData) {
        this.interactionData = interactionData;

    }

    @Override
    public List<Integer> selectNCards(String s, ViewableCardCollection hand, int howMany) {
return (List<Integer>)  interactionData.pop();
    }

    @Override
    public boolean playerQuestionDiceHappy(ViewableDiceCup dice) {
        Boolean x = (Boolean) interactionData.pop();
        return x.booleanValue();
    }

    @Override
    public RomaAction getLayCardAction(String s, ViewableCardCollection hand, ViewableCardCollection diceDiscCards, boolean b) {
        ActionArgs x = (ActionArgs) interactionData.pop();
        return new LayCardAction(new RingInteger0(x.cardNo), new RingInteger0(x.diceNo));
    }

    @Override
    public void printPlayerGameView(PlayerGameView gv) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RomaAction getAction(PlayerGameView gv, PlayerState playerState) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int chooseTakeCardCard(ViewableCardDeck deck) {
        String chosen = (String) interactionData.pop();
        RingInteger0 r = new RingInteger0(0, deck.size());
        try {
            while(true) {
                if(deck.getCard(r).getName().equalsIgnoreCase(chosen)) {
                    return r.asInt();
                }


                r.iterate();
            }
        } catch (allDoneException e) {
            return 0;
        }
    }
}
