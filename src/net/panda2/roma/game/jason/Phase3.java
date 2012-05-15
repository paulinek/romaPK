package net.panda2.roma.game.jason;


public class Phase3 {
/*
	private Game g;

	private State state;

	public Phase3 (Game g, State state) {
		this.g = g;
		this.state = state;
	}

	public void run(Player p) {

		int input = 0;

		do {

			state.showState();
			input = PlayerInput.choice();

			if (input == 1) {
				layCards();
			} 
			else if (input == 2) {
				takeMoney();
			}
			else if (input == 3) {
				takeCards();
			}
			else if (input == 4) {
				activateCard();
			}
			else if (input == 5) {
				bribeActivate();
			}
			else if (input == 6) {
				break;
			}


		} while (input != 6);
		
		if (g.board().getEssedumActivation() > 0) {
			g.board().addDefenseOffset(-2*g.board().getEssedumActivation(), p);
			g.board().setEssedumActivation(0);
			assert (g.board().getDefenseOffset(p) >= 0);
		}

		//TODO end of turn actions

	}
	
	private void bribeActivate() {
		
		Dice dice = null;
		do {
		dice = g.input().chooseDie();
		}
		while (dice.getScore() > g.currentPlayer().assets().sestertii());
		
		g.currentPlayer().assets().decSestertii(dice.getScore());
		g.board().diceDisc(Constant.NUM_DICE_DISCS + 1).activateCard(g.currentPlayer());
		
		
		
	}


	private void takeMoney() {
		Dice d = g.input().chooseDie();
		int count = d.getScore();
		d.useDice();
		g.currentPlayer().assets().incSestertii(count);

	}
	*/
}
