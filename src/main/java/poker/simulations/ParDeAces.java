package poker.simulations;

import poker.*;
import poker.listeners.JuegosGanadores;
import poker.listeners.PairListener;
import poker.repartirstrategies.Default;
import poker.repartirstrategies.RepartirStrategy;

import java.util.List;
import java.util.Set;

public class ParDeAces extends Simulator {

    private static final int SIMULATIONS = 100000;

    private static final int PLAYERS = 3; //23 Max con un mazo

    public static void main(String[] args) {
        new ParDeAces().simulate();
    }

    @Override
    protected int getNumberOfPlayers() {
        return PLAYERS;
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player yo = null;

    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return (mazo, commonCards, playerCards) -> {
            yo = playerCards.get(0);

            yo.setCards(Card.of(Suit.Spades, Rank.ACE), Card.of(Suit.Clubs, Rank.ACE));
            //yo.setCards(Card.of(Suit.Spades, Rank.TWO), Card.of(Suit.Clubs, Rank.TWO));
            mazo.removeCards(yo.getCards());

            for (Player playerCard: playerCards) {
                if(playerCard != yo){
                    playerCard.receiveCards(mazo);
                }
            }

            commonCards.receiveCards(mazo);
        };
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int yoCounter = 0;

            @Override
            public void raiseEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    if (holdem.getGanadores().size() == 1) {
                        if (holdem.getGanadores().contains(yo)) {
                            yoCounter++;
                        }
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("% de veces que gano con par de ACEs = \t\t\t" + String.format("%3.2f%%", 100f * (float) yoCounter / (float) SIMULATIONS));
            }
        });
    }
}
