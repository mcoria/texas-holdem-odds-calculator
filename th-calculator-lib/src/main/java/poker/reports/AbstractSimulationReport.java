package poker.reports;

import poker.CommunityCards;
import poker.Player;
import poker.Simulator;
import poker.reports.listeners.HoldemStatics;

import java.util.List;

public abstract class AbstractSimulationReport {

    protected abstract List<HoldemStatics> setupEventListeners();

    protected abstract List<Player> createPlayers();

    protected abstract CommunityCards createCommunityCards();

    protected abstract int getNumberOfSimulations();

    public void simulate() {
        Simulator simulator = new Simulator();

        simulator.setCommunityCards(createCommunityCards());

        List<Player> players = createPlayers();
        simulator.setPlayers(players);

        List<HoldemStatics> listeners = setupEventListeners();
        simulator.setListeners(listeners);

        int numberOfSimulations = getNumberOfSimulations();
        simulator.setNumberOfSimulations(numberOfSimulations);

        simulator.simulate();

        printStatics(listeners, players, numberOfSimulations);
    }



    private void printStatics(List<HoldemStatics> listeners, List<Player> players, int numberOfSimulations) {
        System.out.println("Total games = " + numberOfSimulations);
        System.out.println("Players = " + players.size());

        for (HoldemStatics listener : listeners) {
            System.out.println("===========================" + listener.getClass().getName() + "===========================");
            listener.printStatics();
        }
    }
}
