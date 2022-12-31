package poker.simulations;

import poker.CommunityCards;
import poker.Player;
import poker.listeners.*;
import poker.players.MayorA;

import java.util.ArrayList;
import java.util.List;

public class Contienda extends AbstractSimulationReport{
    private static final int SIMULATIONS = 1000000;


    public static void main(String[] args) {
        new Contienda().simulate();
    }

    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player());                               // Optimista
        players.add(new Player().setDefaultCallResponse(false)); // Pesimista
        players.add(new MayorA());                               // Mayor a Q
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
