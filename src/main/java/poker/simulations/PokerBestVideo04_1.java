package poker.simulations;

import poker.*;
import poker.repartirstrategies.RepartirStrategy;

import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=456
 *
 * SI CONVERGE
 */


public class PokerBestVideo04_1 extends Simulator {
    private static final int SIMULATIONS = 100000;

    private static final int PLAYERS = 3;

    public static void main(String[] args) {
        new PokerBestVideo04_1().simulate();
    }

    @Override
    protected int getNumberOfPlayers() {
        return PLAYERS;
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player faraz;

    private Player josh;

    private Player daniel;


    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            faraz = playerCards.get(0);
            josh = playerCards.get(1);
            daniel = playerCards.get(2);

            // Faraz  =    9(clubs)      7(hearts)              -  1%
            faraz.setCards(Card.of(Suit.Clubs, Rank.NINE), Card.of(Suit.Hearts, Rank.SEVEN));
            mazo.removeCards(faraz.getCards());

            // Josh   =    Q(spades)     5(hearts)              - 15%
            josh.setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Hearts, Rank.FIVE));
            mazo.removeCards(josh.getCards());

            // Scotty =   10(diamonds)   9(spades)              - out
            mazo.removeCards(Set.of(Card.of(Suit.Diamonds, Rank.TEN), Card.of(Suit.Spades, Rank.NINE)));

            // Shawn  =   10(hearts)     4(clubs)               - out
            mazo.removeCards(Set.of(Card.of(Suit.Hearts, Rank.TEN), Card.of(Suit.Clubs, Rank.FOUR)));

            // Daniel =    J(clubs)      7(clubs)               - 31%
            daniel.setCards(Card.of(Suit.Clubs, Rank.JACK), Card.of(Suit.Clubs, Rank.SEVEN));
            mazo.removeCards(daniel.getCards());

            // Flop =      J(spades)     3(hearts)       3(diamons)
            commonCards.setFlop(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Hearts, Rank.THREE), Card.of(Suit.Diamonds, Rank.THREE));
            mazo.removeCards(commonCards.getCards());

            commonCards.setTurn(mazo.getRandomCard());
            commonCards.setRiver(mazo.getRandomCard());
        };
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int farazCounter = 0;
            private int joshCounter = 0;
            private int danielCounter = 0;

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

                        if (holdem.getGanadores().contains(daniel)) {
                            danielCounter++;
                        }
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("Faraz = \t\t" + String.format("%3.2f%%", 100f * (float) farazCounter / (float) SIMULATIONS));
                System.out.println("Josh = \t\t\t" + String.format("%3.2f%%", 100f * (float) joshCounter / (float) SIMULATIONS));
                System.out.println("Daniel = \t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
            }
        });
    }
}
