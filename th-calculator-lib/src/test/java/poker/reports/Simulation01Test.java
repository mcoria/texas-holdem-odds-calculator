package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Simulation01Test {

    private static final int SIMULATIONS = 10000;


    @Before
    public void setup() {
        player1 = new Player()
                .setPocketCards(Card.of(Suit.CLUBS, Rank.KING), Card.of(Suit.SPADES, Rank.TWO));

        player2 = new Player()
                .setPocketCards(Card.of(Suit.HEARTS, Rank.NINE), Card.of(Suit.DIAMONDS, Rank.EIGHT));

        listener = new MyHoldemListener();
    }

    private Player player1;
    private Player player2;

    private MyHoldemListener listener;


    class MyHoldemListener implements HoldemListener {
        protected int player1Counter = 0;
        protected int player2Counter = 0;
        int tie = 0;

        @Override
        public void catchEvent(HoldemEvents event, Holdem holdem) {
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
    }

    @Test
    public void test1() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards());

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(54f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(45f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(1f, (100 * listener.tie) / SIMULATIONS, 2);

    }

    @Test
    public void test2() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));

        simulator.setCommunityCards(new CommunityCards()
                .setFlop(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.SPADES, Rank.FOUR), Card.of(Suit.CLUBS, Rank.SEVEN))
        );

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(73f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(26f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(1f, (100 * listener.tie) / SIMULATIONS, 2);

    }

    @Test
    public void test3() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards()
                .setFlop(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.SPADES, Rank.FOUR), Card.of(Suit.CLUBS, Rank.SEVEN))
                .setTurn(Card.of(Suit.SPADES, Rank.NINE))
        );

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(7f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(93f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(1f, (100 * listener.tie) / SIMULATIONS, 2);

    }

    @Test
    public void test4() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards()
                .setFlop(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.SPADES, Rank.FOUR), Card.of(Suit.CLUBS, Rank.SEVEN))
                .setTurn(Card.of(Suit.SPADES, Rank.NINE))
                .setRiver(Card.of(Suit.SPADES, Rank.KING))
        );

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(100f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(0f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(0f, (100 * listener.tie) / SIMULATIONS, 2);
    }

    @Test
    public void test_fromPlayer1PointOfView() {
        player2 = new Player();

        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards()
                .setFlop(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.SPADES, Rank.FOUR), Card.of(Suit.CLUBS, Rank.SEVEN))
                .setTurn(Card.of(Suit.SPADES, Rank.NINE))
                .setRiver(Card.of(Suit.SPADES, Rank.KING))
        );

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(72f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(24f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(3f, (100 * listener.tie) / SIMULATIONS, 2);
    }

    @Test
    public void test_fromPlayer2PointOfView() {
        player1 = new Player();

        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards()
                .setFlop(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.SPADES, Rank.FOUR), Card.of(Suit.CLUBS, Rank.SEVEN))
                .setTurn(Card.of(Suit.SPADES, Rank.NINE))
                .setRiver(Card.of(Suit.SPADES, Rank.KING))

        );

        simulator.setPlayers(List.of(player1, player2));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(35f, (100 * listener.player1Counter) / SIMULATIONS, 2);
        assertEquals(63f, (100 * listener.player2Counter) / SIMULATIONS, 2);
        assertEquals(1f, (100 * listener.tie) / SIMULATIONS, 2);
    }

}
