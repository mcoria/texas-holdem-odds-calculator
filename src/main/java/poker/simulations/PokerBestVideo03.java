package poker.simulations;

import poker.*;
import poker.repartirstrategies.RepartirStrategy;

import java.util.List;
import java.util.Set;

/*
 *
 * https://youtu.be/gD-FQBpT2o0?t=395
 *
 * Faraz  = -  6(clubs)      7(hearts)              - 16%
 * Josh   = -  K(diamonds)   Q(hearts)              - 51%
 * Scotty =    6(spades)     4(hearts) - se va
 * Shawn  =    A(clubs)      K(hearts) - se va
 * Daniel =    J(spades)     7(spades)              - 31%
 * SI CONVERGE !!!!
 */


public class PokerBestVideo03 extends Simulator {
    private static final int SIMULATIONS = 100000;
    private static final int PLAYERS = 3;

    public static void main(String[] args) {
        new PokerBestVideo03().simulate();
    }

    @Override
    protected int getNumberOfPlayers() {
        return PLAYERS;
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player daniel;

    private Player josh;

    private Player faraz;

    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            daniel = playerCards.get(0);
            josh = playerCards.get(1);
            faraz = playerCards.get(2);


            daniel.setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Spades, Rank.SEVEN));
            josh.setCards(Card.of(Suit.Diamonds, Rank.KING), Card.of(Suit.Hearts, Rank.QUEEN));
            faraz.setCards(Card.of(Suit.Hearts, Rank.SEVEN), Card.of(Suit.Clubs, Rank.SIX));


            mazo.removeCards(josh.getCards());
            mazo.removeCards(daniel.getCards());
            mazo.removeCards(faraz.getCards());

            //* Scotty =    6(spades)     4(hearts) - se va
            mazo.removeCards(Set.of(Card.of(Suit.Spades, Rank.SIX), Card.of(Suit.Hearts, Rank.FOUR)));

            //* Shawn  =    A(clubs)      K(hearts) - se va
            mazo.removeCards(Set.of(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Hearts, Rank.KING)));

            commonCards.receiveCards(mazo);
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
        });
    }
}
