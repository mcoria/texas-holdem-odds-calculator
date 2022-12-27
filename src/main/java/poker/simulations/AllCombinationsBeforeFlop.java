package poker.simulations;

import poker.*;
import poker.listeners.Combinacion;
import poker.listeners.HoldemStatics;

import java.util.*;

public class AllCombinationsBeforeFlop extends AbstractSimulationReport {

    private static final int SIMULATIONS = 1000000;

    private static final int PLAYERS = 8; //23 Max con un mazo

    public static void main(String[] args) {
        new AllCombinationsBeforeFlop().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player observer = new Player();


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
    protected List<HoldemStatics> setupEventListeners() {
        return List.of(new Combinacion());
    }

}
