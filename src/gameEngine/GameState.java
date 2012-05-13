package gameEngine;

import Roma.AuthToken;
import Roma.RomaException;
import Roma.RomaUnAuthException;
import card.Card;
import card.CardDeck;
import card.Legat;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: pacchi
 * Date: 17/04/12
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */

public class GameState {
    int numPlayers;
    PlayerState player[];
    VictoryPoints tabletopVPStockpile;
    CardDeck maindeck, discard;
    GameEngine ge;
    RomaRules ruleset;
    private GameState(RomaRules ruleSet, GameEngine ge) throws RomaException {
        //ref rules for numPlayers
        numPlayers=ruleSet.numPlayers;
        this.ge = ge;
        this.ruleset = ruleset;
        // player is an array of size numPlayers
        player=new PlayerState[numPlayers];
        // create a new gameEngine.PlayerState object for each of numPlayers
        int i;
        for (i=0; i<numPlayers; i++){
            player[i]=new PlayerState(ruleSet, ge);
        }

        // seed stockpile with init VP
        tabletopVPStockpile= new VictoryPoints(ruleSet.tableInitVP, ruleSet.minVP);

        if (sanityCheckInitVPTotal(ruleSet)!=true){
            throw new RomaException("Sanity Check Failed: VP init doesn't add up");
        }
        maindeck = new CardDeck();
        discard = new CardDeck();
        seedCards(maindeck);
    }

    public VictoryPoints getVP(AuthToken tk) throws RomaException {
            if(ge.authenticateToken(tk)) {
                return tabletopVPStockpile;
            } else {
                throw new RomaUnAuthException();
            }
    }
    private void seedCards(CardDeck d) {
        // this function creates a bunch of card objects
        Vector<Card> cards = new Vector<Card>()
        int i;
        for(i = 0; i < ruleset.LegatCount; i++ ) {
            cards.add(new Legat());
        }

        d.seedCards(cards);

    }
    private boolean sanityCheckInitVPTotal(RomaRules ruleSet) {
        // VP post-init sanity check
        // get pointsTotal from each player
        // get pointsTotal from tabletop stockpile
        // add them all up, and game total should be rules.gameTotalVP

        boolean saneQ=true;

        int gameTotalVPSoFar=0;
        int j;
        for (j=0; j<player.length; j++){
            gameTotalVPSoFar+=player[j].vp.getPointsTotal();
        }
        gameTotalVPSoFar+=tabletopVPStockpile.getPointsTotal();

        if (gameTotalVPSoFar!=ruleSet.gameTotalVP){
            saneQ=false;
        }
        return saneQ;
    }

    public static GameState createGameState(RomaRules rules, GameEngine gameEngine) throws RomaException {
        return new GameState(rules, gameEngine);
    }
}
