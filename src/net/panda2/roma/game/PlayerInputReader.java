package net.panda2.roma.game;

public class PlayerInputReader {

/*

	public DiceDisc chooseDisc () {
		Scanner s = new Scanner (System.in);
		System.out.println("Choose a diceDisc");
		int input = s.nextInt();
		boolean valid = false;
		while (!valid) {
			if (input >= 1 && input <= Constant.NUM_DICE_DISCS + 1) {
				valid = true;
				return g.board().diceDisc(input);
			}
		}
		return null;
	}

    public Card chhoseCardFromDeck(Deck deck) {

    }
	public Card chooseCardFromList (Deck deck) {
		Scanner s = new Scanner(System.in);
		System.out.println("Choose a card");
		boolean valid = false;
		Card c = null;
		while (!valid) {
			String input = s.next();

			c = nameToCard(input, deck);
			if (c == null) {
				System.out.println("invalid input, retry");
			} else {
				valid = true;
			}
		}
		return c;
	}

	private Card nameToCard (String name, Deck deck) {
		name = name.toLowerCase();
		for (int i = 0; i < deck.size(); i++) {
			if (deck.lookCardAtIndex(i).getName().toLowerCase().equals(name)) {
				return deck.removeCardAtIndex(i);
			}
		}
		return null;
	}

	//name to card

	public Dice chooseDie () {
		Scanner s = new Scanner(System.in);
		System.out.println("Choose a dice by value");
		int input;
		int diceNum = 1;
		boolean valid = false;
		while (!valid) {
			input = s.nextInt();
			for (int i = 1; i <= Constant.NUM_ACTION_DICE; i++) {
				if (g.board().actionDice(i).getScore() == input) {
					if (g.board().actionDice(i).isUsed() == false) {
						diceNum = i;
						valid = true;
						break;
					}
				}
			}
			if (!valid) {
				System.out.println("Invalid dice choice");
			}
		}
		return g.board().actionDice(diceNum);
	}
*/
}
