package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;
import poker.players.PlayerWithCards;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerBestVideo01Test {
    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        daniel = new PlayerWithCards()
                // Daniel =    2(Hearts)        2(Diamonds)
                .setPocketCards(Card.of(Suit.HEARTS, Rank.TWO), Card.of(Suit.DIAMONDS, Rank.TWO));

        scotty = new PlayerWithCards()
                // Scotty =    K(Spades)       Q(Clubs)
                .setPocketCards(Card.of(Suit.SPADES, Rank.KING), Card.of(Suit.CLUBS, Rank.QUEEN));

        faraz = new PlayerWithCards()
                // Faraz  =    Q(Diamonds)      6(Clubs)
                .setPocketCards(Card.of(Suit.DIAMONDS, Rank.QUEEN), Card.of(Suit.CLUBS, Rank.SIX));

        josh = new PlayerWithCards()
                // Josh  =    7(Clubs)          4(Diamonds)
                .setPocketCards(Card.of(Suit.CLUBS, Rank.SEVEN), Card.of(Suit.DIAMONDS, Rank.FOUR));

        shawn = new PlayerWithCards()
                // Shawn  =    8(Spades)        4(Clubs)
                .setPocketCards(Card.of(Suit.SPADES, Rank.EIGHT), Card.of(Suit.CLUBS, Rank.FOUR));

        listener = new MyHoldemListener();
    }

    private Player daniel;

    private Player scotty;

    private Player faraz;

    private Player josh;

    private Player shawn;

    private MyHoldemListener listener;


    class MyHoldemListener implements HoldemListener {
        protected int danielCounter = 0;
        protected int scottyCounter = 0;

        @Override
        public void catchEvent(HoldemEvent event, Holdem holdem) {
            if (event.equals(HoldemEvent.FINISHED)) {
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
    }

    @Test
    public void test1() {
        Simulator simulator = new Simulator();
        simulator.setListeners(List.of(listener));
        simulator.setCommunityCards(new CommunityCards());

        faraz.setPlayerStrategy((player, event, holdem) -> false);
        josh.setPlayerStrategy((player, event, holdem) -> false);
        shawn.setPlayerStrategy((player, event, holdem) -> false);

        simulator.setPlayers(List.of(daniel, scotty, faraz, josh, shawn));
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.simulate();

        assertEquals(52f, ( 100 * listener.danielCounter ) / SIMULATIONS, 2);
        assertEquals(47f, ( 100 * listener.scottyCounter ) / SIMULATIONS, 2);

    }

}
