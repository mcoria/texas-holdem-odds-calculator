package poker.simulations;

import poker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombinationsBeforeFlop extends AbstractSimulationReport {

    private static final int SIMULATIONS = 100000;

    private static final int PLAYERS = 8; //23 Max con un mazo

    public static void main(String[] args) {
        new CombinationsBeforeFlop().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player observer = new Player()
            .setClearCardsOnRest(false)
            .setCards(Card.of(Suit.Spades, Rank.ACE), Card.of(Suit.Clubs, Rank.ACE));
            //setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Clubs, Rank.KING));
            //setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Clubs, Rank.QUEEN));
            //setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Clubs, Rank.JACK));
            //setCards(Card.of(Suit.Spades, Rank.TEN), Card.of(Suit.Clubs, Rank.TEN));
            //setCards(Card.of(Suit.Spades, Rank.NINE), Card.of(Suit.Clubs, Rank.NINE));
            //setCards(Card.of(Suit.Spades, Rank.EIGHT), Card.of(Suit.Clubs, Rank.EIGHT));
            //setCards(Card.of(Suit.Spades, Rank.SEVEN), Card.of(Suit.Clubs, Rank.SEVEN));
            //setCards(Card.of(Suit.Spades, Rank.SIX), Card.of(Suit.Clubs, Rank.SIX));
            //setCards(Card.of(Suit.Spades, Rank.FIVE), Card.of(Suit.Clubs, Rank.FIVE));
            //setCards(Card.of(Suit.Spades, Rank.FOUR), Card.of(Suit.Clubs, Rank.FOUR));
            //setCards(Card.of(Suit.Spades, Rank.THREE), Card.of(Suit.Clubs, Rank.THREE));
            //setCards(Card.of(Suit.Spades, Rank.TWO), Card.of(Suit.Clubs, Rank.TWO));


            // Prospect escalera
            //setCards(Card.of(Suit.Spades, Rank.ACE), Card.of(Suit.Clubs, Rank.KING));
            //setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Clubs, Rank.QUEEN));
            //setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Clubs, Rank.JACK));
            //setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Clubs, Rank.TEN));
            //setCards(Card.of(Suit.Spades, Rank.TEN), Card.of(Suit.Clubs, Rank.NINE));
            //setCards(Card.of(Suit.Spades, Rank.NINE), Card.of(Suit.Clubs, Rank.EIGHT));


            // Prospect color
            //setCards(Card.of(Suit.Spades, Rank.ACE), Card.of(Suit.Spades, Rank.NINE));
            //setCards(Card.of(Suit.Spades, Rank.KING), Card.of(Suit.Spades, Rank.EIGHT));
            //setCards(Card.of(Suit.Spades, Rank.QUEEN), Card.of(Suit.Spades, Rank.SEVEN));
            //setCards(Card.of(Suit.Spades, Rank.JACK), Card.of(Suit.Spades, Rank.SIX));
            //setCards(Card.of(Suit.Spades, Rank.TEN), Card.of(Suit.Spades, Rank.FIVE));


            // Juegos Bajos
            //setCards(Card.of(Suit.Spades, Rank.SEVEN), Card.of(Suit.Clubs, Rank.TWO));
            //setCards(Card.of(Suit.Spades, Rank.THREE), Card.of(Suit.Clubs, Rank.TWO));


    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(observer);

        for (int i = 0; i < PLAYERS - 1; i++) {
            players.add(new Player());
        }
        return players;
    }

    @Override
    protected CommonCards createCommonCards() {
        return new CommonCards();
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new EventListener() {
            private int observerCounter = 0;

            @Override
            public void catchEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    if (holdem.getGanadores().contains(observer)) {
                        observerCounter++;
                    }
                }
            }

            @Override
            public void printStatics() {
                System.out.println("Probabilidad de ganar con xxx en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) observerCounter / (float) SIMULATIONS));
            }

            @Override
            public Map<String, Object> getStatics() {
                return null;
            }
        });
    }
}
