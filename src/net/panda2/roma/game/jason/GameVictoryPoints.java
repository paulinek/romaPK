package net.panda2.roma.game.jason;

public class GameVictoryPoints {
	
private int gameVictoryPoints;
	
	public GameVictoryPoints () {
		this.gameVictoryPoints = 0;
	}
	
	public int getGameVictoryPoints () {
		return this.gameVictoryPoints;
	}
	
	public void incVictoryPoints (int incAmt) {
		this.gameVictoryPoints += incAmt;
	}
	
	public void decVictoryPoints (int decAmt) {
		this.gameVictoryPoints -= decAmt;
	}
}
