package poker.simulations;

import poker.*;
import poker.repartirstrategies.RepartirStrategy;

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
    private static final int PLAYERS = 2;

    public static void main(String[] args) {
        new PokerBestVideo01().simulate();
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
    private Player scotty;

    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            daniel = playerCards.get(0);
            scotty = playerCards.get(1);

            // Faraz  =    Q(Diamonds)      6(Clubs)            -  out
            mazo.removeCards(Set.of(Card.of(Suit.Diamonds, Rank.QUEEN), Card.of(Suit.Clubs, Rank.SIX)));

            // Josh  =    7(Clubs)          4(Diamonds)         -  out
            mazo.removeCards(Set.of(Card.of(Suit.Clubs, Rank.SEVEN), Card.of(Suit.Diamonds, Rank.FOUR)));

            // Scotty =    K(Spades)       Q(Clubs)             - 47%
            scotty.setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Clubs, Rank.QUEEN)); //Scotty
            mazo.removeCards(scotty.getCards());

            // Shawn  =    8(Spades)        4(Clubs)            -  out
            mazo.removeCards(Set.of(Card.of(Suit.Spades, Rank.EIGHT), Card.of(Suit.Clubs, Rank.FOUR)));

            // Daniel =    2(Hearts)        2(Diamonds)         - 52%
            daniel.setCards(Card.of(Suit.Hearts, Rank.TWO), Card.of(Suit.Diamonds, Rank.TWO)); //Daniel
            mazo.removeCards(daniel.getCards());

            commonCards.receiveCards(mazo);
        };
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int danielCounter = 0;
            private int scottyCounter = 0;

            @Override
            public void raiseEvent(HoldemEvents event, Holdem holdem) {
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
