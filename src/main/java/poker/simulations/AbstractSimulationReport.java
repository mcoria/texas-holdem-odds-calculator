package poker.simulations;

import poker.CommonCards;
import poker.EventListener;
import poker.Player;
import poker.Simulator;

import java.util.List;

public abstract class AbstractSimulationReport {

    protected abstract List<EventListener> setupEventListeners();

    protected abstract List<Player> createPlayers();

    protected abstract CommonCards createCommonCards();

    protected abstract int getNumberOfSimulations();

    public void simulate() {
        Simulator simulator = new Simulator();

        simulator.setCommonCards(createCommonCards());

        List<Player> players = createPlayers();
        simulator.setPlayers(players);

        List<EventListener> listeners = setupEventListeners();
        simulator.setListeners(listeners);

        int numberOfSimulations = getNumberOfSimulations();
        simulator.simulate(numberOfSimulations);

        printStatics(listeners, players.size(), numberOfSimulations);
    }



    private void printStatics(List<EventListener> listeners, int numberOfPlayers, int numberOfSimulations) {
        System.out.println("Total games = " + numberOfSimulations);
        System.out.println("Players = " + numberOfPlayers);
        for (EventListener listener : listeners) {
            System.out.println("===========================" + listener.getClass().getName() + "===========================");
            listener.printStatics();
        }
    }
}
