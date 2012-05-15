package net.panda2.game.dice;

import java.util.Random;
//  yes, I know dice is plural, but if I named this class "Die" there would be some upset markers

// Class Dice
// implements a single die of n sides (defaults to 6)

public class Dice {
	
	protected int score;
    int sides;

    Random roller;

	public Dice (int sides) {
        this.sides = sides;
        roller = new Random();
	}



	public int getScore () {
		return this.score;
	}
	
	public int roll () {
		this.score = roller.nextInt(sides) + 1;
	    return score;
    }

}
