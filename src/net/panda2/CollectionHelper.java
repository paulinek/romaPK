package net.panda2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 15/05/12
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionHelper {

    public static <E> ArrayList<E> arrayToarrayList(E[] arr) {
        ArrayList<E> al = new ArrayList<E>(arr.length);
        for(E e: arr) {
            al.add(e);
        }
        return al;
    }
}
