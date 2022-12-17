package poker.simulations;

import poker.EventListener;
import poker.Simulator;
import poker.listeners.PairListener;
import poker.listeners.JuegosGanadores;
import poker.repartirstrategies.Default;
import poker.repartirstrategies.RepartirStrategy;

import java.util.List;

public class Aleatorio extends Simulator {

    private static final int SIMULATIONS = 100000;

    private static final int PLAYERS = 2; //23 Max con un mazo

    public static void main(String[] args) {
        new Aleatorio().simulate();
    }

    @Override
    protected int getNumberOfPlayers() {
        return PLAYERS;
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    @Override
    protected RepartirStrategy getRepartirStrategy() {
        return new Default();
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new PairListener(), new JuegosGanadores());
    }
}
