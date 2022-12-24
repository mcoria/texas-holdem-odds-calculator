package poker.simulations;

import poker.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=395
 *
 * SI CONVERGE !!!!
 */


public class PokerBestVideo03 extends AbstractSimulationReport {
    private static final int SIMULATIONS = 100000;
    private static final int PLAYERS = 3;

    public static void main(String[] args) {
        new PokerBestVideo03().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player daniel = new Player() {
        @Override
        public void setCards() {
            //* Daniel =    J(spades)     7(spades)              - 31%
            setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Spades, Rank.SEVEN));
        }
    };

    private Player scotty = new Player() {
        @Override
        public void setCards() {
            //* Scotty =    6(spades)     4(hearts)             - se va
            setCards(Card.of(Suit.Spades, Rank.SIX), Card.of(Suit.Hearts, Rank.FOUR));
        }
    }.setCallResponse(false);

    private Player faraz = new Player() {
        @Override
        public void setCards() {
            // Faraz  = -  6(clubs)      7(hearts)              - 16%
            setCards(Card.of(Suit.Hearts, Rank.SEVEN), Card.of(Suit.Clubs, Rank.SIX));
        }
    };

    private Player josh = new Player() {
        @Override
        public void setCards() {
            // Josh =  K(diamonds)   Q(hearts)                  - 51%
            setCards(Card.of(Suit.Diamonds, Rank.KING), Card.of(Suit.Hearts, Rank.QUEEN));
        }
    };

    private Player shawn = new Player() {
        @Override
        public void setCards() {
            //* Shawn  =    A(clubs)      K(hearts)             - se va
            setCards(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Hearts, Rank.KING));
        }
    }.setCallResponse(false);

    private CommonCards commonCards = new CommonCards();

    @Override
    protected List<Player> createPlayers() {
        return List.of(daniel, scotty, faraz, josh, shawn);
    }

    @Override
    protected CommonCards createCommonCards() {
        return commonCards;
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int farazCounter = 0;
            private int joshCounter = 0;
            private int danielCounter = 0;

            @Override
            public void catchEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    if (holdem.getGanadores().size() == 1) {
                        if (holdem.getGanadores().contains(daniel)) {
                            danielCounter++;
                        }
                        if (holdem.getGanadores().contains(josh)) {
                            joshCounter++;
                        }
                        if (holdem.getGanadores().contains(faraz)) {
                            farazCounter++;
                        }
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("Daniel = \t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
                System.out.println("Josh = \t\t\t" + String.format("%3.2f%%", 100f * (float) joshCounter / (float) SIMULATIONS));
                System.out.println("Faraz = \t\t" + String.format("%3.2f%%", 100f * (float) farazCounter / (float) SIMULATIONS));
            }

            @Override
            public Map<String, Object> getStatics() {
                return null;
            }
        });
    }
}
