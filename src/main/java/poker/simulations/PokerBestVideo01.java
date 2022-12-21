package poker.simulations;

import poker.*;


import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=175
 *
 * SI CONVERGE
 */

public class PokerBestVideo01 extends Simulator {
    private static final int SIMULATIONS = 100000;

    public static void main(String[] args) {
        new PokerBestVideo01().simulate();
    }


    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player daniel = new Player() {
        @Override
        public void setCards() {
            // Daniel =    2(Hearts)        2(Diamonds)         - 52%
            setCards(Card.of(Suit.Hearts, Rank.TWO), Card.of(Suit.Diamonds, Rank.TWO)); //Daniel
        }
    };

    private Player scotty = new Player() {
        @Override
        public void setCards() {
            // Scotty =    K(Spades)       Q(Clubs)             - 47%
            setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Clubs, Rank.QUEEN));
        }
    };

    private Player faraz = new Player(false) {
        @Override
        public void setCards() {
            // Faraz  =    Q(Diamonds)      6(Clubs)            -  out
            setCards(Card.of(Suit.Diamonds, Rank.QUEEN), Card.of(Suit.Clubs, Rank.SIX));
        }
    };

    private Player josh = new Player(false) {
        @Override
        public void setCards() {
            // Josh  =    7(Clubs)          4(Diamonds)         -  out
            setCards(Card.of(Suit.Clubs, Rank.SEVEN), Card.of(Suit.Diamonds, Rank.FOUR));
        }
    };

    private Player shawn = new Player(false) {
        @Override
        public void setCards() {
            // Shawn  =    8(Spades)        4(Clubs)            -  out
            setCards(Card.of(Suit.Spades, Rank.EIGHT), Card.of(Suit.Clubs, Rank.FOUR));
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
            private int danielCounter = 0;
            private int scottyCounter = 0;

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
                System.out.println("Daniel = \t\t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
                System.out.println("Scotty = \t\t\t" + String.format("%3.2f%%", 100f * (float) scottyCounter / (float) SIMULATIONS));
            }
        });
    }
}
