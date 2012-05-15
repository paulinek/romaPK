package net.panda2.roma.card;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BuildingCard extends PJRomaCard {

    public BuildingCard(String name, int price, int defense) {
        super(name, price, defense, false);
    }
}
