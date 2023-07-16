package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo03Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new Player()
                //* Daniel =    J(spades)     7(spades)              - 31%
                .setPocketCards(Card.of(Suit.SPADES, Rank.JACK), Card.of(Suit.SPADES, Rank.SEVEN));

        scotty = new Player()
                //* Scotty =    6(spades)     4(hearts)             - se va
                .setPocketCards(Card.of(Suit.SPADES, Rank.SIX), Card.of(Suit.HEARTS, Rank.FOUR));

        faraz = new Player()
                // Faraz  = -  6(clubs)      7(hearts)              - 16%
                .setPocketCards(Card.of(Suit.HEARTS, Rank.SEVEN), Card.of(Suit.CLUBS, Rank.SIX));

        josh = new Player()
                // Josh =  K(diamonds)   Q(hearts)                  - 51%
                .setPocketCards(Card.of(Suit.DIAMONDS, Rank.KING), Card.of(Suit.HEARTS, Rank.QUEEN));

        shawn = new Player()
                //* Shawn  =    A(clubs)      K(hearts)             - se va
                .setPocketCards(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.HEARTS, Rank.KING));

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

        protected int danielCounter = 0;

        @Override
        public void catchEvent(HoldemEvents event, Holdem holdem) {
            if (event.equals(HoldemEvents.FINISHED)) {
                if (holdem.getGanadores().size() == 1) {
                    if (holdem.getGanadores().contains(daniel)) {
                        danielCounter++;
                    }

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

        scotty.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));

        simulator.simulate(SIMULATIONS);

        assertEquals(31f, ( 100 * listener.danielCounter ) / SIMULATIONS, 2);
        assertEquals(16f, ( 100 * listener.farazCounter ) / SIMULATIONS, 2);
        assertEquals(51f, ( 100 * listener.joshCounter ) / SIMULATIONS, 2);
    }

}
