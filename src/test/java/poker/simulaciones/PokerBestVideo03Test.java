package poker.simulaciones;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo03Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new Player()
                //* Daniel =    J(spades)     7(spades)              - 31%
                .setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Spades, Rank.SEVEN));

        scotty = new Player()
                //* Scotty =    6(spades)     4(hearts)             - se va
                .setCards(Card.of(Suit.Spades, Rank.SIX), Card.of(Suit.Hearts, Rank.FOUR));

        faraz = new Player()
                // Faraz  = -  6(clubs)      7(hearts)              - 16%
                .setCards(Card.of(Suit.Hearts, Rank.SEVEN), Card.of(Suit.Clubs, Rank.SIX));

        josh = new Player()
                // Josh =  K(diamonds)   Q(hearts)                  - 51%
                .setCards(Card.of(Suit.Diamonds, Rank.KING), Card.of(Suit.Hearts, Rank.QUEEN));

        shawn = new Player()
                //* Shawn  =    A(clubs)      K(hearts)             - se va
                .setCards(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Hearts, Rank.KING));

        listener = new MyEventListener();
    }

    private Player daniel;

    private Player scotty;

    private Player faraz;

    private Player josh;

    private Player shawn;

    private MyEventListener listener;


    class MyEventListener implements EventListener {
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

        @Override
        public void printStatics() {
        }

        @Override
        public Map<String, Object> getStatics() {
            return null;
        }
    }

    @Test
    public void test1() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommonCards(new CommonCards());

        scotty.setCallPredicate((p, e) -> false);
        shawn.setCallPredicate((p, e) -> false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));

        simulator.simulate(SIMULATIONS);

        assertEquals(31f, ( 100 * listener.danielCounter ) / SIMULATIONS, 2);
        assertEquals(16f, ( 100 * listener.farazCounter ) / SIMULATIONS, 2);
        assertEquals(51f, ( 100 * listener.joshCounter ) / SIMULATIONS, 2);
    }

}
