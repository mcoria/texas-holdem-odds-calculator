package poker.simulations;

import poker.CommonCards;
import poker.EventListener;
import poker.Player;
import poker.Simulator;
import poker.listeners.HoldemStatics;

import java.util.List;

public abstract class AbstractSimulationReport {

    protected abstract List<HoldemStatics> setupEventListeners();

    protected abstract List<Player> createPlayers();

    protected abstract CommonCards createCommonCards();

    protected abstract int getNumberOfSimulations();

    public void simulate() {
        Simulator simulator = new Simulator();

        simulator.setCommonCards(createCommonCards());

        List<Player> players = createPlayers();
        simulator.setPlayers(players);

        List<HoldemStatics> listeners = setupEventListeners();
        simulator.setListeners(listeners);

        int numberOfSimulations = getNumberOfSimulations();
        simulator.simulate(numberOfSimulations);

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
