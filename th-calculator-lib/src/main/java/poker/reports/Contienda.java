package poker.reports;

import poker.CommunityCards;
import poker.Player;
import poker.players.DefaultPlayer;
import poker.players.MayorA;
import poker.reports.listeners.HoldemStatics;
import poker.reports.listeners.PointsStatics;

import java.util.ArrayList;
import java.util.List;

public class Contienda extends AbstractSimulationReport {
    private static final int SIMULATIONS = 10000;


    public static void main(String[] args) {
        new Contienda().simulate();
    }

    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new DefaultPlayer());                                                       // Optimista
        players.add(new DefaultPlayer().setPlayerStrategy((player, event, holdem) -> false));   // Pesimista
        players.add(new DefaultPlayer().setPlayerStrategy(new MayorA()));                       // Mayor a Q
        return players;
    }

    @Override
    protected List<HoldemStatics> setupEventListeners() {
        return List.of(new PointsStatics());
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
