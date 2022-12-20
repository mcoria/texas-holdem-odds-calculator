package poker;

import java.util.List;

public abstract class Simulator {

    protected abstract int getNumberOfSimulations();

    protected abstract List<EventListener> setupEventListeners();

    protected abstract List<Player> createPlayers();

    protected abstract CommonCards createCommonCards();

    public void simulate() {
        List<Player> players = createPlayers();

        List<EventListener> listeners = setupEventListeners();

        Holdem holdem = new Holdem( players, createCommonCards()  );

        for (EventListener listener : listeners) {
            holdem.addListener(listener);
        }

        int simulations = getNumberOfSimulations();
        for (int i = 0; i < simulations; i++) {
            holdem.play();
        }

        printStatics(listeners, players.size(), simulations);
    }

    private void printStatics(List<EventListener> listeners, int numberOfPlayers, int simulations) {
        System.out.println("Total games = " + simulations);
        System.out.println("Players = " + numberOfPlayers);
        for (EventListener listener : listeners) {
            System.out.println("===========================" + listener.getClass().getName() + "===========================");
            listener.printStatics();
        }
    }
}
