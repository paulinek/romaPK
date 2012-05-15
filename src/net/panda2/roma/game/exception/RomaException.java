package net.panda2.roma.game.exception;

/**
* Created with IntelliJ IDEA.
* User: pacchi
* Date: 14/05/12
* Time: 1:22 AM
* To change this template use File | Settings | File Templates.
*/
public class RomaException extends Exception {

    void doConstructor(String s) {

    }
    public RomaException(String s){
         doConstructor(s);
    }
    public RomaException() {
            doConstructor("Default Roma Exception");
    }
}
