package poker.simulaciones;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo01Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new Player() {
            @Override
            public void setCards() {
                // Daniel =    2(Hearts)        2(Diamonds)
                setCards(Card.of(Suit.Hearts, Rank.TWO), Card.of(Suit.Diamonds, Rank.TWO)); //Daniel
            }
        };

        scotty = new Player() {
            @Override
            public void setCards() {
                // Scotty =    K(Spades)       Q(Clubs)
                setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Clubs, Rank.QUEEN));
            }
        };

        faraz = new Player() {
            @Override
            public void setCards() {
                // Faraz  =    Q(Diamonds)      6(Clubs)
                setCards(Card.of(Suit.Diamonds, Rank.QUEEN), Card.of(Suit.Clubs, Rank.SIX));
            }
        };

        josh = new Player() {
            @Override
            public void setCards() {
                // Josh  =    7(Clubs)          4(Diamonds)
                setCards(Card.of(Suit.Clubs, Rank.SEVEN), Card.of(Suit.Diamonds, Rank.FOUR));
            }
        };

        shawn = new Player() {
            @Override
            public void setCards() {
                // Shawn  =    8(Spades)        4(Clubs)
                setCards(Card.of(Suit.Spades, Rank.EIGHT), Card.of(Suit.Clubs, Rank.FOUR));
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
        protected int danielCounter = 0;
        protected int scottyCounter = 0;

        @Override
        public void catchEvent(HoldemEvents event, Holdem holdem) {
            if (event.equals(HoldemEvents.FINISHED)) {
                if (holdem.getGanadores().size() == 1) {
                    if (holdem.getGanadores().contains(daniel)) {
                        danielCounter++;
                    }
                    if (holdem.getGanadores().contains(scotty)) {
                        scottyCounter++;
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

        faraz.setCallResponse(false);
        josh.setCallResponse(false);
        shawn.setCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));

        simulator.simulate(SIMULATIONS);

        assertEquals(52f, ( 100 * listener.danielCounter ) / SIMULATIONS, 2);
        assertEquals(47f, ( 100 * listener.scottyCounter ) / SIMULATIONS, 2);

    }

}
