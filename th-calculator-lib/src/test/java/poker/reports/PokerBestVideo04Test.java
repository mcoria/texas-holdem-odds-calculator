package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo04Test {
    private static final int SIMULATIONS = 1000;

    @Before
    public void setup() {
        daniel = new Player()
                .setPocketCards(Card.of(Suit.CLUBS, Rank.JACK), Card.of(Suit.CLUBS, Rank.SEVEN));

        scotty = new Player()
                .setPocketCards(Card.of(Suit.DIAMONDS, Rank.TEN), Card.of(Suit.SPADES, Rank.NINE));

        faraz = new Player()
                .setPocketCards(Card.of(Suit.CLUBS, Rank.NINE), Card.of(Suit.HEARTS, Rank.SEVEN));

        josh = new Player()
                .setPocketCards(Card.of(Suit.SPADES, Rank.QUEEN), Card.of(Suit.HEARTS, Rank.FIVE));

        shawn = new Player()
                .setPocketCards(Card.of(Suit.HEARTS, Rank.TEN), Card.of(Suit.CLUBS, Rank.FOUR));

        listener = new MyHoldemListener();
    }

    private Player daniel;

    private Player scotty;

    private Player faraz;

    private Player josh;

    private Player shawn;

    private MyHoldemListener listener;

    class MyHoldemListener implements HoldemListener {
        protected int farazCounter = 0;
        protected int joshCounter = 0;
        protected int danielCounter = 0;

        @Override
        public void catchEvent(HoldemEvents event, Holdem holdem) {
            if (event.equals(HoldemEvents.FINISHED)) {
                if (holdem.getGanadores().size() == 1) {
                    if (holdem.getGanadores().contains(daniel)) {
                        danielCounter++;
                    }
                    if (holdem.getGanadores().contains(faraz)) {
                        farazCounter++;
                    }
                    if (holdem.getGanadores().contains(josh)) {
                        joshCounter++;
                    }
                }
            }
        }
    }

    @Test
    public void test1() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards());

        scotty.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);

        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(33f, (100 * listener.danielCounter) / SIMULATIONS, 2);
        assertEquals(16f, (100 * listener.farazCounter) / SIMULATIONS, 2);
        assertEquals(47f, (100 * listener.joshCounter) / SIMULATIONS, 2);
    }

    @Test
    public void test2() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));

        CommunityCards communityCards = new CommunityCards();
        communityCards.setFlop(Card.of(Suit.SPADES, Rank.JACK), Card.of(Suit.HEARTS, Rank.THREE), Card.of(Suit.DIAMONDS, Rank.THREE));

        simulator.setCommunityCards(communityCards);

        scotty.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(84f, (100 * listener.danielCounter) / SIMULATIONS, 2);
        assertEquals(1f, (100 * listener.farazCounter) / SIMULATIONS, 2);
        assertEquals(15f, (100 * listener.joshCounter) / SIMULATIONS, 2);
    }

    @Test
    public void test3() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));

        CommunityCards communityCards = new CommunityCards()
                .setFlop(Card.of(Suit.SPADES, Rank.JACK), Card.of(Suit.HEARTS, Rank.THREE), Card.of(Suit.DIAMONDS, Rank.THREE))
                .setTurn(Card.of(Suit.CLUBS, Rank.TEN));

        simulator.setCommunityCards(communityCards);

        scotty.setDefaultCallResponse(false);
        faraz.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(92f, (100 * listener.danielCounter) / SIMULATIONS, 2);
        assertEquals(8f, (100 * listener.joshCounter) / SIMULATIONS, 2);
    }

    @Test
    public void test4() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));

        CommunityCards communityCards = new CommunityCards()
                .setFlop(Card.of(Suit.SPADES, Rank.JACK), Card.of(Suit.HEARTS, Rank.THREE), Card.of(Suit.DIAMONDS, Rank.THREE))
                .setTurn(Card.of(Suit.CLUBS, Rank.TEN))
                .setRiver(Card.of(Suit.CLUBS, Rank.QUEEN));

        simulator.setCommunityCards(communityCards);

        scotty.setDefaultCallResponse(false);
        faraz.setDefaultCallResponse(false);
        shawn.setDefaultCallResponse(false);
        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(0f, (100 * listener.danielCounter) / SIMULATIONS, 2);
        assertEquals(100f, (100 * listener.joshCounter) / SIMULATIONS, 2);
    }

}
