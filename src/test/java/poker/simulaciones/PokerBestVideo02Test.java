package poker.simulaciones;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo02Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new Player() {
            @Override
            public void setCards() {
                // Daniel =     K(Spades)         5(Hearts)    -  out
                setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Hearts, Rank.FIVE));
            }
        };

        scotty = new Player() {
            @Override
            public void setCards() {
                // Scotty =    Q(Hearts)        8(Hearts)    -  out
                setCards(Card.of(Suit.Hearts, Rank.QUEEN), Card.of(Suit.Hearts, Rank.EIGHT));
            }
        };

        faraz = new Player() {
            @Override
            public void setCards() {
                // Faraz =      K(Clubs)        2(Hearts)      -  24%
                setCards(Card.of(Suit.Clubs, Rank.KING), Card.of(Suit.Hearts, Rank.TWO));
            }
        };

        josh = new Player() {
            @Override
            public void setCards() {
                // Josh =       A(Hearts)       K(Hearts)       -  75%
                setCards(Card.of(Suit.Hearts, Rank.ACE), Card.of(Suit.Hearts, Rank.KING));
            }
        };

        shawn = new Player() {
            @Override
            public void setCards() {
                // Shawn =     8(Clubs)         4(Spades)    -  out
                setCards(Card.of(Suit.Clubs, Rank.EIGHT), Card.of(Suit.Spades, Rank.FOUR));
            }
        };

        commonCards = new CommonCards();

        listener = new MyEventListener();
    }

    private Player daniel;

    private Player scotty;

    private Player faraz;

    private Player josh;

    private Player shawn;
    private CommonCards commonCards;

    private MyEventListener listener;


    class MyEventListener implements EventListener {
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
        simulator.setCommonCards(commonCards);

        daniel.setCallResponse(false);
        scotty.setCallResponse(false);
        shawn.setCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));

        simulator.simulate(SIMULATIONS);

        assertEquals(24f, ( 100 * listener.farazCounter ) / SIMULATIONS, 2);
        assertEquals(75f, ( 100 * listener.joshCounter ) / SIMULATIONS, 2);

    }

}
