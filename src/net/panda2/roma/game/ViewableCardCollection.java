package net.panda2.roma.game;

import net.panda2.roma.card.CardView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 16/05/12
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewableCardCollection {
    public List<CardView>   getCardView();

    int howManyOfThese(String name);
}
