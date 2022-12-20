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
            faraz = playerCards.get(0);
            josh = playerCards.get(1);
            daniel = playerCards.get(2);


            // Faraz  =    9(clubs)      7(hearts)              -  16%
            faraz.setCards(Card.of(Suit.Clubs, Rank.NINE), Card.of(Suit.Hearts, Rank.SEVEN));
            mazo.removeCards(faraz.getCards());

            // Josh   =    Q(spades)     5(hearts)              - 16%
            josh.setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Hearts, Rank.FIVE));
            mazo.removeCards(josh.getCards());

            // Scotty =   10(diamonds)   9(spades)              - out
            mazo.removeCards(Set.of(Card.of(Suit.Diamonds, Rank.TEN), Card.of(Suit.Spades, Rank.NINE)));

            // Shawn  =   10(hearts)     4(clubs)               - out
            mazo.removeCards(Set.of(Card.of(Suit.Hearts, Rank.TEN), Card.of(Suit.Clubs, Rank.FOUR)));

            // Daniel =    J(clubs)      7(clubs)               - 33%
            daniel.setCards(Card.of(Suit.Clubs, Rank.JACK), Card.of(Suit.Clubs, Rank.SEVEN));
            mazo.removeCards(daniel.getCards());

            commonCards.receiveCards(mazo);
        };
    }
*/
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
                System.out.println("Josh = \t\t\t" + String.format("%3.2f%%", 100f * (float) joshCounter / (float) SIMULATIONS));
                System.out.println("Faraz = \t\t" + String.format("%3.2f%%", 100f * (float) farazCounter / (float) SIMULATIONS));
                System.out.println("Daniel = \t\t" + String.format("%3.2f%%", 100f * (float) danielCounter / (float) SIMULATIONS));
            }
        });
    }
}
