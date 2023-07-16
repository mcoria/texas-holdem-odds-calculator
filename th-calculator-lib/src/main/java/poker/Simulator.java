package poker;

import java.util.List;

public class Simulator {
    private List<? extends HoldemListener> listeners;

    private List<Player> players;

    private CommunityCards communityCards;

    private int numberOfSimulations = 10000;

    public void simulate() {
        Holdem holdem = new Holdem(players, communityCards);

        for (HoldemListener listener : listeners) {
            holdem.addListener(listener);
        }

        for (int i = 0; i < numberOfSimulations; i++) {
            holdem.reset();
            holdem.play();
        }
    }


    public void setListeners(List<? extends HoldemListener> listeners) {
        this.listeners = listeners;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCommunityCards(CommunityCards communityCards) {
        this.communityCards = communityCards;
    }

    public void setNumberOfSimulations(int numberOfSimulations) {
        this.numberOfSimulations = numberOfSimulations;
    }
}
