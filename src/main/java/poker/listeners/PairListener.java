package poker.listeners;

import poker.EventListener;
import poker.Holdem;
import poker.Player;
import poker.Rank;

import java.util.Map;

public class PairListener implements EventListener {
    private int games = 0;
    private int pairOfACEs = 0;
    private int pairOfACEsGanador = 0;
    private int pair = 0;
    private int pairGanador = 0;

    private final Player observer;

    public PairListener(Player observer) {
        this.observer = observer;
    }

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.NEW_GAME)) {
            games++;
        } else if (event.equals(HoldemEvents.CARTAS_REPARETIDAS)) {

            if (isPair(observer)) {
                pair++;

                if (isPairOfValue(observer, Rank.ACE)) {
                    pairOfACEs++;
                }
            }
        } else if (event.equals(HoldemEvents.FINISHED)) {
            if (holdem.getGanadores().contains(observer)) {
                if (isPair(observer)) {
                    pairGanador++;

                    if (isPairOfValue(observer, Rank.ACE)) {
                        pairOfACEsGanador++;
                    }
                }
            }
        }
    }

    private boolean isPair(Player player) {
        if (player.getCards().stream().map(card -> card.getRank()).distinct().count() == 1l) {
            return true;
        }
        return false;
    }

    private boolean isPairOfValue(Player player, Rank rank) {
        if (player.getCards().stream().filter(theCard -> rank.equals(theCard.getRank())).count() == 2l) {
            return true;
        }
        return false;
    }

    @Override
    public void printStatics() {
        System.out.println("Probabilidad de recibir par en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) pair / (float) games));
        System.out.println("\tProbabilidad de recibir par de aces = \t\t" + String.format("%3.2f%%", 100f * (float) pairOfACEs / (float) games));

        System.out.println("Probabilidad de ganar con par en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) pairGanador / (float) pair));
        System.out.println("\tProbabilidad de ganar con par de aces = \t\t" + String.format("%3.2f%%", 100f * (float) pairOfACEsGanador / (float) pairOfACEs));
    }

    @Override
    public Map<String, Object> getStatics() {
        return null;
    }
}

