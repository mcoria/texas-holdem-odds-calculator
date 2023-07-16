package poker.analysis;

import poker.*;
import poker.players.DefaultPlayer;
import poker.players.PlayerWithCards;

import java.util.ArrayList;
import java.util.List;

public class OddsCalculator {
    private static final int SIMULATIONS = 10000;
    private final CommunityCards communityCards;
    private final PlayerWithCards observer;
    private int partidosGanados = 0;
    private int numberOfPlayers;

    public OddsCalculator() {
        this.communityCards = new CommunityCards();
        this.observer = new PlayerWithCards();
    }

    public OddsCalculator setFlop(Card card1, Card card2, Card card3) {
        communityCards.setFlop(card1, card2, card3);
        return this;
    }

    public OddsCalculator setTurn(Card card) {
        communityCards.setTurn(card);
        return this;
    }

    public OddsCalculator setRiver(Card card) {
        communityCards.setRiver(card);
        return this;
    }

    public OddsCalculator setPocketCards(Card card1, Card card2) {
        observer.setPocketCards(card1, card2);
        return this;
    }


    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public float calculateOds(){
        Simulator simulator = new Simulator();

        List<Player> playerList = new ArrayList<>();
        playerList.add(observer);
        for (int i = 1; i < numberOfPlayers; i++) {
            playerList.add(new DefaultPlayer());
        }

        simulator.setPlayers(playerList);
        simulator.setCommunityCards(communityCards);
        simulator.setNumberOfSimulations(SIMULATIONS);

        simulator.setListeners(List.of((event, holdem) -> {
            if (HoldemListener.HoldemEvent.FINISHED.equals(event) || HoldemListener.HoldemEvent.FINISHED_ABANDONO.equals(event)) {
                if(holdem.getGanadores().contains(observer)){
                    partidosGanados++;
                }
            }
        }));
        simulator.simulate();

        return (float) partidosGanados / SIMULATIONS;
    }
}
