package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;
import poker.players.PlayerWithCards;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo02Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new PlayerWithCards()
                // Daniel =     K(Spades)         5(Hearts)    -  out
                .setPocketCards(Card.of(Suit.SPADES, Rank.KING), Card.of(Suit.HEARTS, Rank.FIVE));


        scotty = new PlayerWithCards()
                // Scotty =    Q(Hearts)        8(Hearts)    -  out
                .setPocketCards(Card.of(Suit.HEARTS, Rank.QUEEN), Card.of(Suit.HEARTS, Rank.EIGHT));

        faraz = new PlayerWithCards()
                // Faraz =      K(Clubs)        2(Hearts)      -  24%
                .setPocketCards(Card.of(Suit.CLUBS, Rank.KING), Card.of(Suit.HEARTS, Rank.TWO));

        josh = new PlayerWithCards()
                // Josh =       A(Hearts)       K(Hearts)       -  75%
                .setPocketCards(Card.of(Suit.HEARTS, Rank.ACE), Card.of(Suit.HEARTS, Rank.KING));

        shawn = new PlayerWithCards()
                // Shawn =     8(Clubs)         4(Spades)    -  out
                .setPocketCards(Card.of(Suit.CLUBS, Rank.EIGHT), Card.of(Suit.SPADES, Rank.FOUR));

        listener = new MyHoldemListener();
    }

    private Player daniel;

    private Player scotty;

    private Player faraz;

    private Player josh;

    private Player shawn;

    private MyHoldemListener listener;


    class MyHoldemListener implements HoldemListener {
        protected int farazCounter = 0;
        protected int joshCounter = 0;

        @Override
        public void catchEvent(HoldemEvents event, Holdem holdem) {
            if (event.equals(HoldemEvents.FINISHED)) {
                if (holdem.getGanadores().size() == 1) {
                    if (holdem.getGanadores().contains(faraz)) {
                        farazCounter++;
                    }

                    if (holdem.getGanadores().contains(josh)) {
                        joshCounter++;
                    }
                }
            }
        }
    }

    @Test
    public void test1() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards());

        daniel.setDefaultCallResponse(false);
        scotty.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(24f, ( 100 * listener.farazCounter ) / SIMULATIONS, 2);
        assertEquals(75f, ( 100 * listener.joshCounter ) / SIMULATIONS, 2);

    }

}
