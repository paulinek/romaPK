package net.panda2.roma.card;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 3:35 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CharacterCard extends PJRomaCard {

    public CharacterCard(String name, int price, int defense) {
        super(name, price, defense, true);
    }
}
