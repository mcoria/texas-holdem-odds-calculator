package poker.simulations;

import poker.CommonCards;
import poker.EventListener;
import poker.Player;
import poker.Simulator;
import poker.listeners.JuegosGanadores;
import poker.listeners.PairListener;

import java.util.ArrayList;
import java.util.List;

public class Aleatorio extends Simulator {
    private static final int SIMULATIONS = 1000000;

    private static final int PLAYERS = 3; //23 Max con un mazo

    public static void main(String[] args) {
        new Aleatorio().simulate();
    }

    private Player observer = null;

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < PLAYERS; i++) {
            players.add(new Player());
        }
        observer = players.get(0);
        return players;
    }

    @Override
    protected List<EventListener> setupEventListeners() {
        return List.of(new PairListener(observer), new JuegosGanadores());
    }

    @Override
    protected CommonCards createCommonCards() {
        return new CommonCards();
    }
}
