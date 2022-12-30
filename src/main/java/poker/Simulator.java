package poker;

import java.util.List;

public class Simulator {

    private List<? extends EventListener> listeners;

    private List<Player> players;

    private CommunityCards communityCards;

    public void simulate(int numberOfSimulations) {
        Holdem holdem = new Holdem(players, communityCards);

        for (EventListener listener : listeners) {
            holdem.addListener(listener);
        }

        for (int i = 0; i < numberOfSimulations; i++) {
            holdem.reset();
            holdem.play();
        }
    }


    public void setListeners(List<? extends EventListener> listeners) {
        this.listeners = listeners;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCommonCards(CommunityCards communityCards) {
        this.communityCards = communityCards;
    }
}
