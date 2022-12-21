package poker.simulations;

import poker.*;

import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=447
 *
 * SI CONVERGE
 */


public class PokerBestVideo04_0 extends Simulator {
    private static final int SIMULATIONS = 100000;

    public static void main(String[] args) {
        new PokerBestVideo04_0().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player daniel = new Player() {
        @Override
        public void setCards() {
            // Daniel =    J(clubs)      7(clubs)               - 33%
            setCards(Card.of(Suit.Clubs, Rank.JACK), Card.of(Suit.Clubs, Rank.SEVEN));
        }
    };

    private Player scotty = new Player(false) {
        @Override
        public void setCards() {
            // Scotty =   10(diamonds)   9(spades)              - out
            setCards(Card.of(Suit.Diamonds, Rank.TEN), Card.of(Suit.Spades, Rank.NINE));
        }
    };

    private Player faraz = new Player() {
        @Override
        public void setCards() {
            // Faraz  =    9(clubs)      7(hearts)              -  16%
            setCards(Card.of(Suit.Clubs, Rank.NINE), Card.of(Suit.Hearts, Rank.SEVEN));
        }
    };

    private Player josh = new Player() {
        @Override
        public void setCards() {
            // Josh   =    Q(spades)     5(hearts)              - 47%
            setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Hearts, Rank.FIVE));
        }
    };

    private Player shawn = new Player(false) {
        @Override
        public void setCards() {
            // Shawn  =   10(hearts)     4(clubs)               - out
            setCards(Card.of(Suit.Hearts, Rank.TEN), Card.of(Suit.Clubs, Rank.FOUR));
        }
    };

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
                        if (holdem.getGanadores().contains(faraz)) {
                            farazCounter++;
                        }

                        if (holdem.getGanadores().contains(josh)) {
                            joshCounter++;
                        }

                        if (holdem.getGanadores().contains(daniel)) {
                            danielCounter++;
                        }
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("Josh = \t\t\t" + String.format("%3.2f%%", 100f * (float) joshCounter / (float) SIMULATIONS));
                System.out.println("Faraz = \t\t" + String.format("%3.2f%%", 100f * (float) farazCounter / (float) SIMULATIONS));
                System.out.println("Daniel = \t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
            }
        });
    }
}
