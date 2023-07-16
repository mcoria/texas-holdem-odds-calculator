package poker.reports;

import org.junit.Before;
import org.junit.Test;
import poker.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Simulation02Test {

    private static final int SIMULATIONS = 100000;


    @Before
    public void setup() {
        player1 = new Player();
        player2 = new Player();
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

        assertEquals(49f, ( 100 * listener.player1Counter ) / SIMULATIONS, 2);
        assertEquals(49f, ( 100 * listener.player2Counter ) / SIMULATIONS, 2);
        assertEquals(2f, ( 100 * listener.tie ) / SIMULATIONS, 2);
    }



}
