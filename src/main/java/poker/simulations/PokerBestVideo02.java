package poker.simulations;

import poker.*;
import poker.repartirstrategies.RepartirStrategy;

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
    private static final int PLAYERS = 2;

    public static void main(String[] args) {
        new PokerBestVideo02().simulate();
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

    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            faraz = playerCards.get(0);
            josh = playerCards.get(1);

            // Faraz =      K(Clubs)        2(Hearts)      -  24%
            faraz.setCards(Card.of(Suit.Clubs, Rank.KING), Card.of(Suit.Hearts, Rank.TWO));
            mazo.removeCards(faraz.getCards());

            // Josh =       A(Hearts)       K(Hearts)       -  75%
            josh.setCards(Card.of(Suit.Hearts, Rank.ACE), Card.of(Suit.Hearts, Rank.KING));
            mazo.removeCards(josh.getCards());

            // Scotty =    Q(Hearts)        8(Hearts)    -  out
            mazo.removeCards(Set.of(Card.of(Suit.Hearts, Rank.QUEEN), Card.of(Suit.Hearts, Rank.EIGHT)));

            // Shawn =     8(Clubs)         4(Spades)    -  out
            mazo.removeCards(Set.of(Card.of(Suit.Clubs, Rank.EIGHT), Card.of(Suit.Spades, Rank.FOUR)));

            // Daniel =     K(Spades)         5(Hearts)    -  out
            mazo.removeCards(Set.of(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Hearts, Rank.FIVE)));

            commonCards.receiveCards(mazo);
        };
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
