package net.panda2.roma.game;

import net.panda2.CollectionHelper;
import net.panda2.roma.action.LayCardAction;
import net.panda2.roma.action.RAction;
import net.panda2.roma.card.CardView;
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
 * Date: 15/05/12
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerInteractor {

    Scanner s;
    PrintStream out;

    void     setupReader(InputStream in, OutputStream outputStream) {
        s = new Scanner(in);
        out = new PrintStream(outputStream);
    }

    public PlayerInteractor() {
        setupReader(System.in, System.out);
    }

    public PlayerInteractor(InputStream in) {
        setupReader(in,System.out);
    }

    static    String defaultPrompt = "What would you like to do?";
    static RAction[] actionChoices = {RAction.TAKECARD, RAction.TAKEMONEY,RAction.LAYCARD, RAction.ACTIVATECARD, RAction.ENDTURN};
    static String[] noYes = {"NO", "YES"};

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
        printCardviewList(cv, offset,d);
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

    private void sayNL() {
        out.println();
    }

    private void speak(String s) {
        out.print(s);
    }

    public void printDiceList(String s, List<DiceView> diceView) {

        printDiceList(s,diceView,1);
    }
}
