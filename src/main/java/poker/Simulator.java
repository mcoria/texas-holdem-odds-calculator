package poker;

import java.util.List;

public class Simulator {

    private List<EventListener> listeners;

    private List<Player> players;

    private CommonCards commonCards;

    public void simulate(int numberOfSimulations) {
        Holdem holdem = new Holdem(players, commonCards);

        for (EventListener listener : listeners) {
            holdem.addListener(listener);
        }

        for (int i = 0; i < numberOfSimulations; i++) {
            holdem.reset();
            holdem.play();
        }
    }


    public void setListeners(List<EventListener> listeners) {
        this.listeners = listeners;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCommonCards(CommonCards commonCards) {
        this.commonCards = commonCards;
    }
}
