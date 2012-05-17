package net.panda2.roma.game;

import framework.cards.Card;
import framework.interfaces.GameState;
import net.panda2.roma.card.PJRomaCard;
import net.panda2.roma.game.exception.RomaException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

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
    net.panda2.roma.game.GameState gs;
    public PKGameStateTest() {
        tk = new AuthToken();
        ge = GameEngine.createGameEngine(tk);
    }
    /**
     * Get the current turn's player number
     * <p/>
     * <p>
     * This method will return an integer between 0 and
     * ({@link framework.Rules#NUM_PLAYERS NUM_PLAYERS} - 1), as
     * specified in the GameState interface.
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
     * Gets the GameState's current deck.
     * <p/>
     * <p>
     * The current deck of the GameState is to be returned as a List of
     * Cards. The first item in the list is the next card that would be
     * drawn from the deck, and so on.
     * </p>
     *
     * @return the current GameState deck
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

        Vector<net.panda2.game.card.Card> deck = new Vector<net.panda2.game.card.Card >(cardDeck.getCards());
        Collections.reverse(deck);
        List<Card> list = new Vector<Card>();
        for(net.panda2.game.card.Card c:deck) {
            list.add(getCardFromName(((PJRomaCard) c).getName())) ;

        }
        return list;
    }

    public List<Card> getDeck() {
        return deck2card(ge.gs.maindeck);
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
    @Override
    public void setDeck(List<Card> deck) {
        //To change body of implemented methods use File | Settings | File Templates.
        // TODO:  translate the Card to our private card type
     }

    /**
     * Gets the GameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the GameState is to be returned as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     * @return the current GameState discard pile
     */
    @Override
    public List<Card> getDiscard() {
        return deck2card(ge.gs.discard);
    }

    /**
     * Sets the GameState's current discard pile.
     * <p/>
     * <p>
     * The current discard pile of the GameState is to be given as a
     * List of Cards. The first item in the list is the most recently
     * discarded card, and so on.
     * </p>
     *
     * @param discard the new discard pile of the GameState
     */
    @Override
    public void setDiscard(List<Card> discard) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets a player's current Sestertii.
     * <p/>
     * <p>
     * The current Sestertii (money) of the specified player is returned
     * as an integer. Correct player indexing is discussed in the
     * GameState interface header.
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
     * GameState interface header.
     * </p>
     *
     * @param playerNum which player's Sestertii to set
     * @param amount    the quantity of Sestertii for the player to have
     */
    @Override
    public void setPlayerSestertii(int playerNum, int amount) {

    pN(playerNum).money.giveAway(ge.gs.treasury);
        try {
            ge.gs.treasury.transferAway(pN(playerNum).money, amount);
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
     * GameState interface header.
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
     * integer. Correct player indexing is discussed in the GameState
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
       try {
        pN(playerNum).vp.transferAway(ge.gs.tabletopVPStockpile, current, tk);
        ge.gs.tabletopVPStockpile.transferAway(pN(playerNum).vp, points, tk);
       } catch (RomaException romaException) {

        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the contents of a player's current Hand.
     * <p/>
     * <p>
     * The contents of the hand of the specified player is returned as an
     * unordered collection of Cards. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's hand cards to get
     * @return the contents of the player's hand
     */
    @Override
    public Collection<Card> getPlayerHand(int playerNum) {
        return deck2card(pN(playerNum).hand); }

    /**
     * Sets the contents of a player's current Hand.
     * <p/>
     * <p>
     * The contents of the hand of the specified player is given as an
     * unordered collection of Cards. Correct player indexing is
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's hand cards to set
     * @param hand      the contents of the the player's hand
     */
    @Override
    public void setPlayerHand(int playerNum, Collection<Card> hand) {
        //To change body of implemented methods use File | Settings | File Templates.
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
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's dice disc contents to get
     * @return the cards currently on the player's dice discs
     */
    @Override
    public Card[] getPlayerCardsOnDiscs(int playerNum) {
        return new Card[0];  //To change body of implemented methods use File | Settings | File Templates.
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
     * discussed in the GameState interface header.
     * </p>
     *
     * @param playerNum which player's cards to set
     * @param discCards the cards to be placed on the dice discs
     */
    @Override
    public void setPlayerCardsOnDiscs(int playerNum, Card[] discCards) {
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
}
