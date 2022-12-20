package poker.simulations;

import poker.*;

import java.util.List;

/*
 * Player1 = 	72.84%
 * Player2 = 	26.22%
 * Tie = 		 0.94%
 *
 * SI CONVERGE
 */

public class Simulation01_0 extends Simulator {
    private static final int SIMULATIONS = 100000;

    public static void main(String[] args) {
        new Simulation01_0().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player player1 = new Player() {
        @Override
        public void setCards() {
            setCards(Card.of(Suit.Clubs, Rank.KING), Card.of(Suit.Spades, Rank.TWO));
        }
    };

    private Player player2 = new Player() {
        @Override
        public void setCards() {
            setCards(Card.of(Suit.Hearts, Rank.NINE), Card.of(Suit.Diamonds, Rank.EIGHT));
        }
    };

    private CommonCards commonCards = new CommonCards() {
        @Override
        public void setCards() {
            commonCards.setFlop(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Spades, Rank.FOUR), Card.of(Suit.Clubs, Rank.SEVEN));
        }
    };

    @Override
    protected List<Player> createPlayers() {
        return List.of(player1, player2);
    }

    @Override
    protected CommonCards createCommonCards() {
        return commonCards;
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int player1Counter = 0;
            private int player2Counter = 0;

            private int tie = 0;

            @Override
            public void raiseEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    if (holdem.getGanadores().size() == 1) {
                        if (holdem.getGanadores().contains(player1)) {
                            player1Counter++;
                        }
                        if (holdem.getGanadores().contains(player2)) {
                            player2Counter++;
                        }
                    } else if (holdem.getGanadores().size() == 2) {
                        tie++;
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("Player1 = \t\t\t" + String.format("%3.2f%%", 100f * (float) player1Counter / (float) SIMULATIONS));
                System.out.println("Player2 = \t\t\t" + String.format("%3.2f%%", 100f * (float) player2Counter / (float) SIMULATIONS));
                System.out.println("Tie = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) tie / (float) SIMULATIONS));
            }
        });
    }
}
