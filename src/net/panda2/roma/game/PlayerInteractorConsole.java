package net.panda2.roma.game;

import net.panda2.CollectionHelper;
import net.panda2.RingInteger0;
import net.panda2.RingInteger1;
import net.panda2.roma.action.*;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaInputException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 20/05/12
 * Time: 4:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInteractorConsole extends PlayerInteractor {
    static    String defaultPrompt = "What would you like to do?";
    static RAction[] actionChoices = {RAction.TAKECARD, RAction.TAKEMONEY,RAction.LAYCARD, RAction.ACTIVATECARD, RAction.ENDTURN};
    static String[] noYes = {"NO", "YES"};
    Scanner s;PrintStream out;

    public PlayerInteractorConsole() {
        setupReader(System.in, System.out);
    }
    public PlayerInteractorConsole(InputStream in) {
        setupReader(in, System.out);
    }

    public void printDiceList(String prompt, List<DiceView> dv, int offset) {
    speak(prompt+": ");
    for(int i = 0; i < dv.size(); i++)  {
        speak("Dice"+(i+offset)+":"+dv.get(i).getPrintable()+" | ");
    }
        sayNL();
    }

    public List<Integer> selectNCards(String s, ViewableCardCollection hand, int howMany) {
    List<Integer> ret = new ArrayList<Integer>() {};
    int left=howMany;
    List<CardView> handView = hand.getCardView();
        while(left > 0) {
            say(s);
            printCardviewList(handView, 1, "C");
            say(left + " cards left to choose");
            Integer choice = new Integer(readNumber("", 1, handView.size())-1);
            if(!ret.contains(choice))
            {
            ret.add(choice);
            left--;
            }
        }
        checkArgument(howMany == ret.size());
    return ret;
    }

    public LayCardAction getLayCardAction(String s, ViewableCardCollection hand, ViewableCardCollection tab, boolean free) {
        List<CardView> handView = hand.getCardView();
        List<CardView> tabView = tab.getCardView();

        say(s);
        printCardviewList(handView, 1, "C");
       Integer choice = new Integer(readNumber("", 1, handView.size())-1);
       say("Dice disc?");
        printCardviewList(tabView, 1, "D");
        Integer choice2 = new Integer(readNumber("", 1, tabView.size())-1);

    return new LayCardAction(choice.intValue(), choice2.intValue(), free);
    }

    public void say(String s) {
        out.println(s);
    }


    @Override
    public boolean playerQuestionDiceHappy(ViewableDiceCup dice) {
        printDiceList("You have rolled", dice.getDiceView());
        return yesOrNo("Are you happy with these dice?");

    }


    public RomaAction getAction(PlayerGameView gv, PlayerState playerState) {
            ActionData dat = new ActionData();
            RingInteger1 diceNo = null, discNo=null;
            RingInteger0 cardNo = null;

                RomaAction x = null;

            while(x == null) {
                RAction choice = getActionChoice("What would "+playerState.playerName+" like to do");
                ActionArgs a;
                boolean valid=false;
                switch(choice.mode) {
                    case 0: // no additional data
                        valid=true;
                        break;
                    case 1: // hand card, dice disc
                        cardNo = readNumber0("Enter number of card in hand", playerState.hand.numCards());
                        discNo =  readNumber1("Enter which disc to put it on", playerState.diceDiscCards.getSize() +1);
                        valid=true;
                        break;
                    case 2:
                        diceNo = readNumber1("Enter number of action die to play", playerState.dice.getNDice());

                        discNo = new RingInteger1(playerState.dice.getNth(diceNo.toR0()));
                        if(playerState.dice.isNthUsed(diceNo.toR0())) {
                            say("DICE ALREADY USED TRY AGAIN");
                        } else {
                            valid=true;
                        }
                        break;
                }

                if(valid) {
                    if(choice.equals(RAction.ACTIVATECARD)) {
                        x = new ActivateCardAction(playerState.getDiceValue(diceNo.toR0()), discNo.toR0());
                        PJRomaCard c = playerState.diceDiscCards.get(discNo);
                        switch(c.dataMode) {
                            case 0:
                                break;
                        }
                        x.setActionData(dat);
                    } else if(choice.equals(RAction.LAYCARD)) {
                        x = new LayCardAction(cardNo, discNo.toR0());
                    } else if(choice.equals(RAction.TAKECARD)) {
                        x = new TakeCardAction(playerState.getDiceValue(diceNo.toR0()));
                    } else if(choice.equals(RAction.TAKEMONEY)) {
                        x = new TakeMoneyAction(playerState.getDiceValue(diceNo.toR0()));
                    } else if(choice.equals(RAction.ENDTURN)) {
                        x = new EndTurnAction();
                    }

                }

            }
            return x;
        }

    // read a number from 1 to max
    RingInteger1 readNumber1(String s, int max) {
        return new RingInteger1(readNumber(s, 1, max));
    }

    // read a number from 0..max-1
    RingInteger0 readNumber0(String s,int max) {
        return new RingInteger0(readNumber(s,0,max-1));

    }
    @Override
    public int chooseTakeCardCard(ViewableCardDeck deck) {
        printCardviewList(deck.getCardView(), 1, "C");
        say("Enter card to keep");
        Integer choice = new Integer(readNumber("", 1, deck.size())-1);
        return choice.intValue();
    }

    private RAction getActionChoice(String s) {
        return choice(s);
    }


    private void sayNL() {
        out.println();
    }

    private void speak(String s) {
        out.print(s);
    }

    public void printDiceList(String s, List<DiceView> diceView) {

        printDiceList(s,diceView,1);
    }

    void     setupReader(InputStream in, OutputStream outputStream) {
        s = new Scanner(in);
        out = new PrintStream(outputStream);
    }

    public int number (int fromRange, int toRange) throws RomaInputException {
        return readNumber(defaultPrompt, fromRange, toRange);
    }

    public int readNumber(String prompt, int min, int max) {

        int num = min-1;
        while (num < min || num > max) {
            speak(prompt);
            num = s.nextInt();
        }
        return num;
    }

    public boolean yesOrNo() throws RomaInputException{
        return yesOrNo(defaultPrompt);
    }

    public boolean yesOrNo(String prompt) {
        return (choice(prompt, noYes) > 0);
    }

    public RAction choice() {
        return (RAction) choice(defaultPrompt);
    }

    public RAction choice(String prompt) {
        return (RAction) choice(prompt, actionChoices);
    }

    public Object choice(String prompt, Object[] choices) {
        return choice(prompt, CollectionHelper.arrayToarrayList(choices));

    }

    public int choice(String prompt, String[] choices) {
        return choice(prompt, CollectionHelper.arrayToarrayList(choices));

    }

    void printChoices(String prompt, List<String> choices) {
        say(prompt);
        speak("Valid choices are: ");
        for(String s: choices) {
            speak(s);
            speak(" ");
        }
        sayNL();
    }

    public int choice(String prompt, List<String> choices) {
        ArrayList<String> lc = new ArrayList<String>(choices.size());
        for(String s:choices) {
            lc.add(s.toLowerCase());
        }

        while (true) {
            printChoices(prompt, choices);
            String input = s.next().toLowerCase();

            int index = lc.indexOf(input);
            if(index >= 0) {
                return index;
            }
        }
    }

    public Object choice(String prompt, List<Object> choices) {
        ArrayList<String> choiceStrings = new ArrayList<String>(choices.size());
        for(Object o: choices) {
            choiceStrings.add(o.toString());
        }
        return choices.get(choice(prompt, choiceStrings));
    }

    private void printCardviewList(List<CardView> cv, int offset, String d) {

        for(int i=0; i < cv.size(); i++) {
            CardView c = cv.get(i);
            speak(d + (i + offset)+ " :");
            speak(c.getPrintable());
            speak(" | ");
        }

    }

    private void printCardviewList(String header, List<CardView> cv, int offset, String d) {
        speak(header);
        printCardviewList(cv, offset, d);
        sayNL();

    }

    public void printPlayerGameView(PlayerGameView gv) {
        say("Roma (" + gv.getStashVPs() + " left)");
        say("You have " + gv.getMyVPs() + " VPs");
        say("You have "+ gv.getMyMoney()+ " sestertii");
        printCardviewList("Your opponent ", gv.opponent, 1, "D");
        printCardviewList("You ", gv.getMydiscs(), 1, "D");
        printCardviewList("Your hand", gv.getMyhand(), 1, "C");
        printDiceList("Your dice", gv.getMydice(),1);
    }
}
