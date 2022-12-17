package poker.listeners;

import poker.*;

public class PairListener implements EventListener {
    private int games = 0;
    private int pairOfACEs = 0;
    private int pair = 0;


    @Override
    public void printStatics() {
        System.out.println("Probabilidad de recibir par en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) pair / (float) games));
        System.out.println("\tProbabilidad de recibir par de aces = \t\t" + String.format("%3.2f%%", 100f * (float) pairOfACEs / (float) games));
    }

    @Override
    public void raiseEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.NEW_GAME)) {
            games++;
        } else if (event.equals(HoldemEvents.CARTAS_REPARETIDAS)) {

            if (isPair(holdem.getPlayer(0))) {
                pair++;

                if (isPairOfValue(holdem.getPlayer(0), Rank.ACE)) {
                    pairOfACEs++;
                }
            }

        }
    }

    private boolean isPairOfValue(Player player, Rank rank) {
        if (player.getCards().stream().filter(theCard -> rank.equals(theCard.getRank())).count() == 2l) {
            return true;
        }
        return false;
    }

    private boolean isPair(Player player) {
        if (player.getCards().stream().map(card -> card.getRank()).distinct().count() == 1l) {
            return true;
        }
        return false;
    }
}

