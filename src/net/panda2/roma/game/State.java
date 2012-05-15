package net.panda2.roma.game;
/*
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import framework.cards.Card;
import framework.interfaces.GameState;

import net.panda2.roma.AcceptanceTest.GameState;
import net.panda2.roma.AcceptanceTest.NOT_A_CARD;
import Cards.Card;
import Cards.CardFactory;
import Player.Player;
import net.panda2.roma.game.jason.Constant;
import net.panda2.roma.game.jason.Game;
import net.panda2.roma.game.jason.Player;

public class State implements GameState{

	private Game g;

	private Player p1;
	private Player p2;

	private CardFactory factory;

	public State (Game g, Player p1, Player p2) {
		this.g = g;
		this.p1 = p1;
		this.p2 = p2;
		this.factory = new CardFactory(g);
	}

	@Override
	public int getWhoseTurn() {
		return g.currentPlayer().playerNum();
	}

	@Override
	public void setWhoseTurn(int player) {
		Player p = g.pNumToPlayer (player);
		g.setCurrentPlayer(p);
	}

	@Override
	public List<Card> getDeck() {
		return g.board().deck().ToList();
	}

    /**
     * Sets the GameState's current deck.
     * <p/>
     * <p>
     * The new deck of the GameState is to be given as a List of Cards.
     * The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @param deck the new deck of the GameState
     */
/*
    @Override
    public void setDeck(List<Card> deck) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public void setDeck(List<Card> deck) {
		while (!deck.isEmpty()) {
			g.board().deck().add(deck.remove(0));
			//TODO check if deck is not upside down when moving
		}
	}

	@Override
	public List<Card> getDiscard() {
		return g.board().discardDeck().ToList();
	}

	@Override
	public void setDiscard(List<Card> discard) {
		while (!discard.isEmpty()) {
			g.board().discardDeck().add(discard.remove(0));
			//TODO check if deck is not upside down when moving
		}
	}

	@Override
	public int getPlayerSestertii(int playerNum) {
		Player p = g.pNumToPlayer(playerNum);
		return p.assets().sestertii();
	}

	@Override
	public void setPlayerSestertii(int playerNum, int amount) {
		Player p = g.pNumToPlayer(playerNum);
		p.assets().decSestertii(p.assets().sestertii());//makes 0
		p.assets().incSestertii(amount);

	}

	@Override
	public int getPlayerVictoryPoints(int playerNum) {
		Player p = g.pNumToPlayer(playerNum);
		return p.assets().victoryPoints();
	}

	@Override
	public void setPlayerVictoryPoints(int playerNum, int points) {
		Player p = g.pNumToPlayer(playerNum);
		p.assets().decVictoryPoints(p.assets().victoryPoints());
		p.assets().incVictoryPoints(points);

	}

	@Override
	public Collection<Card> getPlayerHand(int playerNum) {
		Player p = g.pNumToPlayer(playerNum);
		return p.assets().hand().ToList();
	}

	@Override
	public void setPlayerHand(int playerNum, Collection<Card> hand) {
		Player p = g.pNumToPlayer(playerNum);
		Iterator<Card> it = hand.iterator();
		while (it.hasNext()) {
			p.assets().hand().add((it.next()));
		}
	}

	@Override
	public Card[] getPlayerCardsOnDiscs(int playerNum) {
		Player p = g.pNumToPlayer(playerNum);
		Card[] cards = new Card[Constant.NUM_DICE_DISCS];

		for (int i = 0; i < Constant.NUM_DICE_DISCS; i++) {
			if (g.board().diceDisc(i+1).isOccupied(p)) {
				cards[i] = new NOT_A_CARD();
			} else {
				cards[i] = g.board().diceDisc(i+1).lookAtCard(p);
			}
		}
		return cards;
	}

	@Override
	public void setPlayerCardsOnDiscs(int playerNum, Card[] discCards) {
		Player p = g.pNumToPlayer(playerNum);
		Card c = null;

		for (int i = 0; i < Constant.NUM_DICE_DISCS; i++) {
			//System.out.println("5");
			if (discCards[i].getName().equals("NOT_A_CARD")) {
				//TODO

				c = null;
			} else {
				c = discCards[i];
			}
			//System.out.println("6");
			g.board().diceDisc(i+1).placeCard(p, c);
		}

	}

	@Override
	public int[] getActionDice() {
		int[] dices = new int[Constant.NUM_ACTION_DICE];
		for (int i = 0; i < Constant.NUM_ACTION_DICE; i++) {
			dices[i] = g.board().actionDice(i+1).getScore();
		}
		return dices;
	}

	@Override
	public void setActionDice(int[] dice) {
		for (int i = 0; i < Constant.NUM_ACTION_DICE; i++) {
			g.board().actionDice(i+1).setDice(dice[i]);
		}
	}

	@Override
	public int getPoolVictoryPoints() {
		return g.board().gameVictoryPoints().getGameVictoryPoints();
	}

    /**
     * Returns true iff a game has been started AND the game has come to completion
     * otherwise return false.
     *
     * @return whether a game has come to completion
     *//*
    @Override
    public boolean isGameCompleted() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setInitialState () {
		g.board().gameVictoryPoints().decVictoryPoints(g.board().gameVictoryPoints().getGameVictoryPoints());
		g.board().gameVictoryPoints().incVictoryPoints(Constant.NUM_GAME_POINTS);

		p1.assets().incVictoryPoints(Constant.PLAYER_START_POINTS);
		g.board().gameVictoryPoints().decVictoryPoints(Constant.PLAYER_START_POINTS);
		p2.assets().incVictoryPoints(Constant.PLAYER_START_POINTS);
		g.board().gameVictoryPoints().decVictoryPoints(Constant.PLAYER_START_POINTS);

		p1.assets().incSestertii(30);
		p2.assets().incSestertii(30);
		initialDeck ();



		for (int i = 0; i < Constant.INITIAL_CARD_NUM_DRAW; i++) {
			p1.assets().addCard(g.board().deck().popDeck());
			p2.assets().addCard(g.board().deck().popDeck());
		}

		g.setCurrentPlayer(p1);
	}

	private void initialDeck () {
		g.board().deck().add(factory.makeEssedum());
		
		g.board().deck().add(factory.makeNero());
		g.board().deck().add(factory.makeMercator());
		g.board().deck().add(factory.makeSicarius());
		
		for (int i = 0; i < 2; i++) {
			g.board().deck().add(factory.makeEssedum());
			g.board().deck().add(factory.makeVelites());
			g.board().deck().add(factory.makeHaruspex());
			g.board().deck().add(factory.makeScaenicus());
			g.board().deck().add(factory.makeConsul());
			g.board().deck().add(factory.makeLegat());
			g.board().deck().add(factory.makeTribunusPlebis());
			g.board().deck().add(factory.makeCenturio());
			g.board().deck().add(factory.makeArchitectus());
			g.board().deck().add(factory.makeConsiliarius());
			g.board().deck().add(factory.makePraetorianus());
			g.board().deck().add(factory.makeSenator());
			g.board().deck().add(factory.makeGladiator());
		}
		for (int i = 0; i < 3; i++) {
			g.board().deck().add(factory.makeLegionarius());
		}
		
		//g.board().deck().shuffle();


	}

	public void showState() {

		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}

		System.out.printf("It's Player %d's turn\n\n", g.currentPlayer().playerNum());

		showPlayerStats(p1);
		System.out.printf("\n\n\n");
		showBoard();
		System.out.printf("\n\n\n");
		showPlayerStats(p2);
		System.out.printf("\n\n\n");

	}

	private void showPlayerStats(Player p) {
		System.out.printf("Player %d's Victory Points:	%d\n", p.playerNum(), p.assets().victoryPoints());
		System.out.printf("Player %d's Sestertii:		%d\n", p.playerNum(), p.assets().sestertii());


		System.out.printf("\n\n");
		p.assets().hand().show();
	}

	private void showBoard () {
		System.out.println("\t\t"+g.board().gameVictoryPoints().getGameVictoryPoints());
		for (int i = 1; i <= Constant.NUM_DICE_DISCS + 1; i++) {
			if (g.board().diceDisc(i).lookAtCard(p1) == null) {
				System.out.print("\t\t");
			} else {
				System.out.printf("%16s", (g.board().diceDisc(i).lookAtCard(p1).getName()));
			}
			System.out.print("[" + (i) + "]");
			if (g.board().diceDisc(i).lookAtCard(p2) == null) {
				System.out.print("\t\t");
			} else {
				System.out.print((g.board().diceDisc(i).lookAtCard(p2).getName()));
			}
			System.out.println("");
		}
		System.out.print("\tAvailable Dice:\n\t");
		for (int i = 1; i <= Constant.NUM_ACTION_DICE; i++) {
			if (g.board().actionDice(i).isUsed() == false) {
				System.out.print(g.board().actionDice(i).getScore() + "\t");
			} else {
				System.out.print(" \t");
			}
		}
	}



}
*/