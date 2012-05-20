package net.panda2.roma.game;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 1:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class AuthToken {
    int token;
    static Random randomizer = new Random();

    public AuthToken() {
        token = randomizer.nextInt();
    }

    boolean equals(AuthToken tk) {
        if(tk == null) return false;
        return (tk.token == token);
    }
}
