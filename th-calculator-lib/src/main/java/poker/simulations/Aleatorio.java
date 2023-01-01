package poker.simulations;

import poker.CommunityCards;
import poker.Player;
import poker.listeners.*;

import java.util.ArrayList;
import java.util.List;

public class Aleatorio extends AbstractSimulationReport{
    private static final int SIMULATIONS = 1000000;

    private static final int PLAYERS = 2; //23 Max con un mazo

    public static void main(String[] args) {
        new Aleatorio().simulate();
    }

    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < PLAYERS; i++) {
            players.add(new Player());
        }
        return players;
    }

    @Override
    protected List<HoldemStatics> setupEventListeners() {
        return List.of(new PairListener(), new JuegosGanadores(), new PocketCardsGroupingListener(), new PointsStatics());
        // new GamesDebug()
        // new Combinacion()
    }

    @Override
    protected CommunityCards createCommunityCards() {
        return new CommunityCards();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }
}
