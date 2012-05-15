package net.panda2.roma.game;

import net.panda2.CollectionHelper;
import net.panda2.roma.action.RAction;
import net.panda2.roma.card.CardView;
import net.panda2.roma.game.exception.RomaInputException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            out.print(prompt);
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
        return (RAction) choice(defaultPrompt, actionChoices);
    }

    public Object choice(String prompt, Object[] choices) {
        return choice(prompt, CollectionHelper.arrayToarrayList(choices));

    }
    public int choice(String prompt, String[] choices) {
        return choice(prompt, CollectionHelper.arrayToarrayList(choices));

    }
    void printChoices(String prompt, List<String> choices) {
        out.println(prompt);
        out.print("Valid choices are: ");
        for(String s: choices) {
            out.print(s);
            out.print(" ");
        }
        out.println();
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

    private void printCardviewList(List<CardView> cv) {
        for(CardView c : cv) {
            out.print(c.getPrintable());
            out.print(" | ");
        }

    }

    private void printCardviewList(String header, List<CardView> cv) {
        out.print(header);
        printCardviewList(cv);
        out.println();

    }
    public void printPlayerGameView(PlayerGameView gv) {
        out.println("Roma (" + gv.getStashVPs() + " left)");
        out.println("You have " + gv.getMyVPs() + " VPs");
        printCardviewList("Your opponent", gv.opponent);
        printCardviewList("You", gv.getMydiscs());
        printCardviewList("Your hand", gv.getMyhand());
        printDiceList("Your dice", gv.getMydice());
    }

    private void printDiceList(String s, List<DiceView> mydice) {
    out.print(s);
    for(DiceView dv: mydice) {
        out.print(dv.getPrintable());
    }
        out.println();
    }

}