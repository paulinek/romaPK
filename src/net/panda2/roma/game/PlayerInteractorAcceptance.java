package net.panda2.roma.game;

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
        return new LayCardAction(x.cardNo, x.diceNo);
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
        for(int i = 0; i < deck.size(); i++) {
            if(deck.getCard(i).getName().equalsIgnoreCase(chosen)) {
                    return i;
            }
        }

        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
