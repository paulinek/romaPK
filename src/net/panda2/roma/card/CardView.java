package net.panda2.roma.card;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 1:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class CardView {

    String name;
    int price;
    int defense;
    boolean isCharacter;

    protected CardView(){

    }

     CardView(String name, int price, int defense, boolean character) {
        this.name = name;
        this.price = price;
        this.defense = defense;
        isCharacter = character;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isCharacter() {
        return isCharacter;
    }

    public String getPrintable() {
    return name + "(" + price + ","+defense + ")";
    }
    public boolean equals(String s) {
        return s.equalsIgnoreCase(name);
    }
}
