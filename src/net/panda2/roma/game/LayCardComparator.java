package net.panda2.roma.game;

import net.panda2.roma.action.LayCardAction;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/05/12
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LayCardComparator implements Comparator<LayCardAction> {
    @Override
    public int compare(LayCardAction layCardAction, LayCardAction layCardAction1) {
        return layCardAction.getCardNo().compareTo(layCardAction1.getCardNo());

    }
}
