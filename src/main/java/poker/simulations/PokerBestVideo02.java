package poker.simulations;

import poker.*;

import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=242
 *
 * SI CONVERGE
 */

public class PokerBestVideo02 extends Simulator {
    private static final int SIMULATIONS = 100000;

    public static void main(String[] args) {
        new PokerBestVideo02().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player daniel = new Player(false) {
        @Override
        public void setCards() {
            // Daniel =     K(Spades)         5(Hearts)    -  out
            setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Hearts, Rank.FIVE));
        }
    };

    private Player scotty = new Player(false) {
        @Override
        public void setCards() {
            // Scotty =    Q(Hearts)        8(Hearts)    -  out
            setCards(Card.of(Suit.Hearts, Rank.QUEEN), Card.of(Suit.Hearts, Rank.EIGHT));
        }
    };

    private Player faraz = new Player() {
        @Override
        public void setCards() {
            // Faraz =      K(Clubs)        2(Hearts)      -  24%
            setCards(Card.of(Suit.Clubs, Rank.KING), Card.of(Suit.Hearts, Rank.TWO));
        }
    };

    private Player josh = new Player() {
        @Override
        public void setCards() {
            // Josh =       A(Hearts)       K(Hearts)       -  75%
            setCards(Card.of(Suit.Hearts, Rank.ACE), Card.of(Suit.Hearts, Rank.KING));
        }
    };

    private Player shawn = new Player(false) {
        @Override
        public void setCards() {
            // Shawn =     8(Clubs)         4(Spades)    -  out
            setCards(Card.of(Suit.Clubs, Rank.EIGHT), Card.of(Suit.Spades, Rank.FOUR));
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

            @Override
            public void raiseEvent(HoldemEvents event, Holdem holdem) {
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
                System.out.println("Faraz = \t\t" + String.format("%3.2f%%", 100f * (float) farazCounter / (float) SIMULATIONS));
                System.out.println("Josh = \t\t\t" + String.format("%3.2f%%", 100f * (float) joshCounter / (float) SIMULATIONS));
            }
        });
    }
}
