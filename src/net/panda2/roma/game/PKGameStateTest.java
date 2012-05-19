package net.panda2.roma.game;

import framework.cards.Card;
import framework.interfaces.GameState;
import net.panda2.roma.card.CardView;
import net.panda2.roma.card.NullCardView;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.card.cards.*;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 14/05/12
 * Time: 4:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class PKGameStateTest implements GameState {
    AuthToken tk;
    GameEngine ge;
PlayerInteractorAcceptance input;
    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

     boolean gameEnded;

    public PKGameStateTest() {
        tk = new AuthToken();
        input = new PlayerInteractorAcceptance();
        ge = GameEngine.createGameEngine(tk, input);
    ge.createGame();
        }
    /**
     * Get the current turn's player number
     * <p/>
     * <p>
     * This method will return an integer between 0 and
     * ({@link framework.Rules#NUM_PLAYERS NUM_PLAYERS} - 1), as
     * specified in the RomaGameState interface.
     * </p>
     *
     * @return the number of the current player
     */
    @Override
    public int getWhoseTurn() {
        return ge.gs.playerNo.get();
    }

    /**
     * Set the current player.
     * <p/>
     * <p>
     * This method sets which player is currently having a turn. Valid
     * inputs are between 0 and ({@link framework.Rules#NUM_PLAYERS
     * NUM_PLAYERS} - 1) inclusive.
     * </p>
     *
     * @param player the player whose turn it will be
     */
    @Override
    public void setWhoseTurn(int player) {
        ge.gs.setPlayerNo(player,tk);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the RomaGameState's current deck.
     * <p/>
     * <p>
     * The current deck of the RomaGameState is to be returned as a List of
     * Cards. The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @return the current RomaGameState deck
     */
     Card getCardFromName(String name) {
        for(Card c: Card.values()) {
            if(c.toString().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
    List<Card> deck2card(ViewableCardDeck cardDeck) {
        Vector<PJRomaCard> deck = new Vector<PJRomaCard>(cardDeck.getCards());
        Collections.reverse(deck);
        List<Card> list = new Vector<Card>();
        for(PJRomaCard c:deck) {
            list.add(getCardFromName(c.getName())) ;

        }
        return list;
    }

    public List<Card> getDeck() {
        return deck2card(ge.gs.maindeck);
    }

    /**
     * Sets the RomaGameState's current deck.
     * <p/>
     * <p>
     * The new deck of the RomaGameState is to be given as a List of Cards.
     * The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @param deck the new deck of the RomaGameState
     */
    @Override
    public void setDeck(List<Card> deck) {
        Collections.reverse(deck);
        setDeck(ge.gs.maindeck, deck);
     }

    /**
     * Gets the RomaGameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the RomaGameState is to be returned as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     * @return the current RomaGameState discard pile
     */
    @Override
    public List<Card> getDiscard() {
        return deck2card(ge.gs.discard);
    }

    /**
     * Sets the RomaGameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the RomaGameState is to be given as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     */

    void setDeck(ViewableCardDeck cd, List<Card> cards) {
        cd.setDeck(ListTransformer(cards));
    }
    public void setDiscard(List<Card> discard) {
        setDeck(ge.gs.discard, discard);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<PJRomaCard> ListTransformer(List<Card> foocard) {
        List<PJRomaCard> cards = new ArrayList<PJRomaCard>();
        for(Card c : foocard) {
            cards.add(makeRomaCard(c));

        }
        return cards;
    }

    /**
     * Gets a player's current Sestertii.
     * <p/>
     * <p>
     * The current Sestertii (money) of the specified player is returned
     * as an integer. Correct player indexing is discussed in the
     * RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's Sestertii to return
     * @return the player's Sestertii
     */
    @Override
    public int getPlayerSestertii(int playerNum) {
        return pN(playerNum).money.getAmount();
    }


    /**
     * Sets a player's current Sestertii.
     * <p/>
     * <p>
     * The new Sestertii (money) of the specified player is given
     * as an integer. Correct player indexing is discussed in the
     * RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's Sestertii to set
     * @param amount    the quantity of Sestertii for the player to have
     */
    @Override
    public void setPlayerSestertii(int playerNum, int amount) {
    PlayerState p = pN(playerNum);

    p.money.giveAway(ge.gs.treasury);
        try {
            ge.gs.treasury.transferAway(p.money, amount);
        } catch (RomaGameEndException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    PlayerState pN(int pNo) {
        return ge.gs.player[pNo];
    }
    /**
     * Gets a player's current Victory Points.
     * <p/>
     * <p>
     * The current Victory Points of the specified player are returned as
     * an integer. Correct player indexing is discussed in the
     * RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's Victory Points to get
     * @return the player's Victory Points
     */
    @Override
    public int getPlayerVictoryPoints(int playerNum) {
    return pN(playerNum).vp.getAmount();}

    /**
     * Gives a player VPs from the stockpile or give the stockpile VPs from a player.
     * <p/>
     * <p>
     * The new Victory Points of the specified player are given as an
     * integer. Correct player indexing is discussed in the RomaGameState
     * interface header.
     * </p>
     * <p>
     * If the given amount is more than what the player already has,
     * then points need to be removed from the stockpile and given
     * to the player and vice versa.
     * </p>
     *
     * @param playerNum which player's Victory Points to set
     * @param points    the player's Victory Points
     */
    @Override
    public void setPlayerVictoryPoints(int playerNum, int points) {
        int current = getPlayerVictoryPoints(playerNum);
        PlayerState p = pN(playerNum);
        try {
            p.vp.transferAway(ge.gs.tabletopVPStockpile, current-1, tk);
            ge.gs.tabletopVPStockpile.transferAway(p.vp, points-1, tk);
        } catch (RomaGameEndException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the contents of a player's current Hand.
     * <p/>
     * <p>
     * The contents of the hand of the specified player is returned as an
     * unordered collection of Cards. Correct player indexing is
     * discussed in the RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's hand cards to get
     * @return the contents of the player's hand
     */
    @Override
    public Collection<Card> getPlayerHand(int playerNum) {
        return deck2card(pN(playerNum).hand); }

    PJRomaCard makeRomaCard(Card c) {
        checkNotNull(c);
        if(c.name().equalsIgnoreCase("Sicarius")) {
            return new Sicarius(1,1);
        }
        else if(c.name().equalsIgnoreCase("Architectus")) {
            return new Architectus(1,1);
        }
        else if(c.name().equalsIgnoreCase("Consiliarius")) {
            return new Consiliarius(1,1);
        }
        else if(c.name().equalsIgnoreCase("Legat")) {
            return new Legat(1,1);
        }
        else if(c.name().equalsIgnoreCase("Gladiator")) {
            return new Gladiator(1,1);
        }
        else if(c.name().equalsIgnoreCase("Mercator")) {
            return new Mercator(1,1);
        }
        else if(c.name().equalsIgnoreCase("Consul")) {
            return new Consul(1,1);
        }
        else if(c.name().equalsIgnoreCase("Legionarius")) {
            return new Legionarius(1,1);
        }
        else if(c.name().equalsIgnoreCase("Nero")) {
            return new Nero(1,1);
        }
        else if(c.name().equalsIgnoreCase("Praetorianus")) {
            return new Praetorianus(1,1);
        }
        else if(c.name().equalsIgnoreCase("Scaenicus")) {
            return new Scaenicus(1,1);
        }
        else if(c.name().equalsIgnoreCase("Haruspex")) {
            return new Haruspex(1,1);
        }
        else if(c.name().equalsIgnoreCase("Senator")) {
            return new Senator(1,1);
        }
        else if(c.name().equalsIgnoreCase("Velites")) {
            return new Velites(1,1);
        }
        else if(c.name().equalsIgnoreCase("Essedum")) {
            return new Essedum(1,1);
        }
        else if(c.name().equalsIgnoreCase("TribunusPlebis")) {
            return new TribunusPlebis(1,1);
        }
        else if(c.name().equalsIgnoreCase("Centurio")) {
            return new Centurio(1,1);
        }
        else if(c.name().equalsIgnoreCase("Aesculapinum")) {
            return new Aesculapinum(1,1);
        }
        else if(c.name().equalsIgnoreCase("Basilica")) {
            return new Basilica(1,1);
        }
        else if(c.name().equalsIgnoreCase("Machina")) {
            return new Machina(1,1);
        }
        else if(c.name().equalsIgnoreCase("Forum")) {
            return new Forum(1,1);
        }
        else if(c.name().equalsIgnoreCase("Mercatus")) {
            return new Mercatus(1,1);
        }
        else if(c.name().equalsIgnoreCase("Onager")) {
            return new Onager(1,1);
        }
        else if(c.name().equalsIgnoreCase("Templum")) {
            return new Templum(1,1);
        }
        else if(c.name().equalsIgnoreCase("Turris")) {
            return new Turris(1,1);
        }
        else return null;
    }
        /**
        * Sets the contents of a player's current Hand.
        * <p/>
        * <p>
        * The contents of the hand of the specified player is given as an
        * unordered collection of Cards. Correct player indexing is
        * discussed in the RomaGameState interface header.
        * </p>
        *
        * @param playerNum which player's hand cards to set
        * @param hand      the contents of the the player's hand
        */
    @Override
    public void setPlayerHand(int playerNum, Collection<Card> hand) {
        ArrayList<PJRomaCard> cards = new ArrayList<PJRomaCard>();
        for(Card c:hand) {
            if(c != null) {
            PJRomaCard cx = makeRomaCard(c);
            checkNotNull(cx);
            cards.add(cx);
            }
        }
        pN(playerNum).hand.setDeck(cards);
  //      checkArgument(pN(playerNum).hand.getCards().size() == hand.size());

    }

    /**
     * Gets the cards currently laid on a player's dice discs.
     * <p/>
     * <p>
     * The cards on the specified player's dice discs are returned in an
     * array of length {@link framework.Rules#NUM_DICE_DISCS
     * NUM_DICE_DISCS}. The 0th index in the array represents the dice
     * disc of value 1. Dice discs with no card are returned with
     * Card.NOT_A_CARD as their value. Correct player indexing is
     * discussed in the RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's dice disc contents to get
     * @return the cards currently on the player's dice discs
     */
    @Override
    public Card[] getPlayerCardsOnDiscs(int playerNum) {
        PlayerState p = pN(playerNum);
        List<CardView> discCardsList = p.diceDiscCards.getCardView();
        Card[] discCards = new Card[discCardsList.size()];
        for(int i = 0; i < discCards.length; i++) {
            Card c;
            c = cardViewToCard(discCardsList.get(i));
            if(c != null) {
                discCards[i] = c;
            }
        }
        return discCards;
//        return new Card[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Card cardViewToCard(CardView cardView) {
        if(cardView instanceof NullCardView) {
            return Card.NOT_A_CARD;

        } else {
            return getCardFromName(cardView.getName());
        }
    }

    /**
     * Sets the contents of a player's dice discs.
     * <p/>
     * <p>
     * The cards on the specified player's dice discs are given in an
     * array of length {@link framework.Rules#NUM_DICE_DISCS
     * NUM_DICE_DISCS}. The 0th index in the array represents the dice
     * disc of value 1. Dice discs with no card are returned with
     * Card.NOT_A_CARD as their value. Correct player indexing is
     * discussed in the RomaGameState interface header.
     * </p>
     *
     * @param playerNum which player's cards to set
     * @param discCards the cards to be placed on the dice discs
     */
    @Override
    public void setPlayerCardsOnDiscs(int playerNum, Card[] discCards) {
        PlayerState p = pN(playerNum);
        checkArgument(discCards.length == p.diceDiscCards.getSize());
        for(int i=0; i < discCards.length; i++) {
            p.diceDiscCards.setCard(i,makeRomaCard(discCards[i]));
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the current player's action dice values.
     * <p/>
     * <p>
     * The values of the current player's action dice are returned in an
     * array in unspecified order. Dice are to be referred to by their
     * value, <i>not</i> by their position in this array.
     * </p>
     *
     * @return the current player's dice
     */
    @Override
    public int[] getActionDice() {
        return new int[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Sets the current player's action dice values.
     * <p/>
     * <p>
     * The values of the current player's action dice are given in an
     * array in unspecified order. Dice are to be referred to by their
     * value, <i>not</i> by their position in this array.
     * </p>
     *
     * @param dice the new values of the current player's dice
     */
    @Override
    public void setActionDice(int[] dice) {
        ge.getCurrentPlayer(tk).dice.setDice(dice);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns the number of Victory Points not currently held by a
     * player.
     * <p/>
     * <p>
     * The number of victory points not held by any player are returned.
     * This method is included so that the total number of Victory
     * Points in a game can be tested.
     * </p>
     *
     * @return the number of Victory Points not held by any player
     */
    @Override
    public int getPoolVictoryPoints() {
        return ge.gs.tabletopVPStockpile.getAmount();
    }

    /**
     * Returns true iff a game has been started AND the game has come to completion
     * otherwise return false.
     *
     * @return whether a game has come to completion
     */
    @Override
    public boolean isGameCompleted() {

return ge.gs.gameOver;
    }

     PlayerState getCurrentPlayer() {
        return ge.gs.currentPlayer();
    }
}
