package net.panda2.roma.card;

import net.panda2.game.card.Card;
import net.panda2.roma.action.ActionData;
import net.panda2.roma.game.AuthToken;
import net.panda2.roma.game.GameEngine;
import net.panda2.roma.game.exception.RomaException;

public abstract class PJRomaCard extends Card implements Cloneable{
	
	 String name;
	 int price;
	 int defense;
	 int defenseOffset;
    protected int lives;
    boolean isCharacter;
    public int dataMode;

    //private DiceDisc disc;
	
	public PJRomaCard(String name, int price, int defense, boolean isCharacter) {
		this.name = name;
		this.price = price;
		this.defense = defense;
	//	this.disc = null;
		this.isCharacter = isCharacter;
	    defenseOffset=0;
        lives=1;
    }
	
	public String getName () {
		return this.name;
	}
	
	public int getPrice () {
		return this.price;
	}
	
	public int getDefense () {
		return this.defense + this.defenseOffset;
	}
	
	public boolean isCharacter () {
		return this.isCharacter;
	}


	public abstract void activate (GameEngine ge, AuthToken tk, ActionData dat) throws RomaException;

    public String toString() {
        return ("Card"+name);
    }
    public CardView makeCardView() {
        return new CardView(name,price,defense,isCharacter);
    }
    public PJRomaCard duplicate() {
        try {
            return (PJRomaCard) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    // TODO - work out how to make this not public
    public void addDefenseOffset(int i) {
        defenseOffset+=i;
    }
    public void reset() {
        defenseOffset = 0;
    }

    public  void decreaseLives() {
        lives--;
    }

    public boolean isDead() {
        return lives<=0;
    }
}
