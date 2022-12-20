package poker.simulations;

import poker.*;

import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=493
 *
 * SI CONVERGE
 */


public class PokerBestVideo04_3 extends Simulator {
    private static final int SIMULATIONS = 100000;

    public static void main(String[] args) {
        new PokerBestVideo04_3().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }


    private Player daniel = new Player();
    private Player scotty = new Player();
    private Player faraz = new Player();
    private Player josh = new Player();
    private Player shawn = new Player();
    private CommonCards commonCards = new CommonCards();

    @Override
    protected List<Player> createPlayers() {
        return List.of(daniel, scotty, faraz, josh, shawn);
    }

    @Override
    protected CommonCards createCommonCards() {
        return commonCards;
    }

    /*
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            josh = playerCards.get(0);
            daniel = playerCards.get(1);


            // Faraz  =    9(clubs)      7(hearts)              - out
            mazo.removeCards(Set.of(Card.of(Suit.Clubs, Rank.NINE), Card.of(Suit.Hearts, Rank.SEVEN)));

            // Josh   =    Q(spades)     5(hearts)              - 100%
            josh.setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Hearts, Rank.FIVE));
            mazo.removeCards(josh.getCards());

            // Scotty =   10(diamonds)   9(spades)              - out
            mazo.removeCards(Set.of(Card.of(Suit.Diamonds, Rank.TEN), Card.of(Suit.Spades, Rank.NINE)));

            // Shawn  =   10(hearts)     4(clubs)               - out
            mazo.removeCards(Set.of(Card.of(Suit.Hearts, Rank.TEN), Card.of(Suit.Clubs, Rank.FOUR)));

            // Daniel =    J(clubs)      7(clubs)               - 0%
            daniel.setCards(Card.of(Suit.Clubs, Rank.JACK), Card.of(Suit.Clubs, Rank.SEVEN));
            mazo.removeCards(daniel.getCards());

            // Flop =      J(spades)     3(hearts)       3(diamons)
            commonCards.setFlop(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Hearts, Rank.THREE), Card.of(Suit.Diamonds, Rank.THREE));
            commonCards.setTurn(Card.of(Suit.Clubs, Rank.TEN));
            commonCards.setRiver(Card.of(Suit.Clubs, Rank.QUEEN));
            mazo.removeCards(commonCards.getCards());
        };
    }
*/

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int joshCounter = 0;
            private int danielCounter = 0;

            @Override
            public void raiseEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    if (holdem.getGanadores().size() == 1) {
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
                System.out.println("Daniel = \t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
            }
        });
    }
}
