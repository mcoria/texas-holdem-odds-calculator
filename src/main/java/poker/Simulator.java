package poker;

import poker.repartirstrategies.Default;
import poker.repartirstrategies.RepartirStrategy;

import java.util.List;

public abstract class Simulator {
    protected abstract int getNumberOfPlayers();

    protected abstract int getNumberOfSimulations();

    protected abstract RepartirStrategy getRepartirStrategy();

    protected abstract List<EventListener> setupEventListeners();


    public void simulate() {
        List<EventListener> listeners = setupEventListeners();

        Holdem holdem = new Holdem(getNumberOfPlayers(), getRepartirStrategy());

        for (EventListener listener : listeners) {
            holdem.addListener(listener);
        }

        int simulations = getNumberOfSimulations();
        for (int i = 0; i < simulations; i++) {
            holdem.play();
        }

        printStatics(listeners);
    }

    private void printStatics(List<EventListener> listeners) {
        System.out.println("Total games = " + getNumberOfSimulations());
        System.out.println("Players = " + getNumberOfPlayers());
        for (EventListener listener : listeners) {
            System.out.println("===========================" + listener.getClass().getName() + "===========================");
            listener.printStatics();
        }
    }
}
