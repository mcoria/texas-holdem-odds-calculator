package poker.simulations;

import poker.*;


import java.util.List;

/*
 * Player1 = 			100.00%
 * Player2 = 			0.00%
 * Tie = 			    0.00%
 *
 * Aca no hay probabilidades dado que todas las cartas son conocidas
 * Observar las estadisticas desde la perspective de Player1 o Player2 sin conocer las cartas del rival.
 *
 */

public class Simulation01_2 extends Simulator {
    private static final int SIMULATIONS = 100000;
    private static final int PLAYERS = 2;

    public static void main(String[] args) {
        new Simulation01_2().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player player1;
    private Player player2;

    @Override
    protected List<Player> createPlayers() {
        return List.of(player1, player2);
    }

    @Override
    protected CommonCards createCommonCards() {
        return null;
    }

/*
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            player1 = playerCards.get(0);
            player2 = playerCards.get(1);

            player1.setCards(Card.of(Suit.Clubs, Rank.KING), Card.of(Suit.Spades, Rank.TWO));
            mazo.removeCards(player1.getCards());

            player2.setCards(Card.of(Suit.Hearts, Rank.NINE), Card.of(Suit.Diamonds, Rank.EIGHT));
            mazo.removeCards(player2.getCards());

            commonCards.setFlop(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Spades, Rank.FOUR), Card.of(Suit.Clubs, Rank.SEVEN));
            commonCards.setTurn(Card.of(Suit.Spades, Rank.NINE));
            commonCards.setRiver(Card.of(Suit.Spades, Rank.KING));
            mazo.removeCards(commonCards.getCards());


        };
    }*/

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
