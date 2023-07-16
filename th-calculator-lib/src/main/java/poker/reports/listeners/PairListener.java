package poker.reports.listeners;

import poker.Holdem;
import poker.Player;
import poker.Rank;

public class PairListener implements HoldemStatics {
    private int games = 0;

    private int pairOfQs = 0;
    private int pairOfQsGanador = 0;

    private int pairOfKs = 0;
    private int pairOfKsGanador = 0;

    private int pairOfACEs = 0;
    private int pairOfACEsGanador = 0;

    private int pair = 0;
    private int pairGanador = 0;

    @Override
    public void catchEvent(HoldemEvent event, Holdem holdem) {
        if (event.equals(HoldemEvent.NEW_GAME)) {
            games++;
        } else if (event.equals(HoldemEvent.CARTAS_REPARETIDAS)) {
            boolean thereIsPair = false;
            boolean thereIsACEsPair = false;
            boolean thereIsKsPair = false;
            boolean thereIsQsPair = false;
            for (Player player :
                    holdem.getPlayers()) {
                if (isPair(player)) {
                    thereIsPair = true;
                    if (isPairOfValue(player, Rank.ACE)) {
                        thereIsACEsPair = true;
                    } else if (isPairOfValue(player, Rank.KING)) {
                        thereIsKsPair = true;
                    } else if (isPairOfValue(player, Rank.QUEEN)) {
                        thereIsQsPair = true;
                    }
                }
            }
            if (thereIsPair) {
                pair++;
                if (thereIsACEsPair) {
                    pairOfACEs++;
                } else if (thereIsKsPair) {
                    pairOfKs++;
                } else if (thereIsQsPair) {
                    pairOfQs++;
                }
            }
        } else if (event.equals(HoldemEvent.FINISHED)) {
            boolean thereIsPair = false;
            boolean thereIsACEsPair = false;
            boolean thereIsKsPair = false;
            boolean thereIsQsPair = false;
            for (Player player :
                    holdem.getPlayers()) {
                if (holdem.getGanadores().contains(player)) {
                    if (isPair(player)) {
                        thereIsPair = true;
                        if (isPairOfValue(player, Rank.ACE)) {
                            thereIsACEsPair = true;
                        } else if (isPairOfValue(player, Rank.KING)) {
                            thereIsKsPair = true;
                        } else if (isPairOfValue(player, Rank.QUEEN)) {
                            thereIsQsPair = true;
                        }
                    }
                }
            }
            if (thereIsPair) {
                pairGanador++;
                if (thereIsACEsPair) {
                    pairOfACEsGanador++;
                } else if (thereIsKsPair) {
                    pairOfKsGanador++;
                } else if (thereIsQsPair) {
                    pairOfQsGanador++;
                }
            }
        }
    }

    private boolean isPair(Player player) {
        if (player.getPocketCards().stream().map(card -> card.getRank()).distinct().count() == 1l) {
            return true;
        }
        return false;
    }

    private boolean isPairOfValue(Player player, Rank rank) {
        if (player.getPocketCards().stream().filter(theCard -> rank.equals(theCard.getRank())).count() == 2l) {
            return true;
        }
        return false;
    }

    @Override
    public void printStatics() {
        System.out.println("% que alguno de los jugadores obtenga par en mano = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) pair / (float) games));
        System.out.println("% que alguno de los jugadores obtenga par de ACEs en mano = \t\t" + String.format("%3.2f%%", 100f * (float) pairOfACEs / (float) games));
        System.out.println("% que alguno de los jugadores obtenga par de Ks en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) pairOfKs / (float) games));
        System.out.println("% que alguno de los jugadores obtenga par de Qs en mano = \t\t\t" + String.format("%3.2f%%", 100f * (float) pairOfQs / (float) games));

        System.out.println("% que alguno de los jugadores que obtuvo par en mano gane = \t\t\t" + String.format("%3.2f%%", 100f * (float) pairGanador / (float) pair));
        System.out.println("% que alguno de los jugadores que obtuvo par de ACEs en mano gane =\t\t" + String.format("%3.2f%%", 100f * (float) pairOfACEsGanador / (float) pairOfACEs));
        System.out.println("% que alguno de los jugadores que obtuvo par de Ks en mano gane =\t\t" + String.format("%3.2f%%", 100f * (float) pairOfKsGanador / (float) pairOfKs));
        System.out.println("% que alguno de los jugadores que obtuvo par de Qs en mano gane =\t\t" + String.format("%3.2f%%", 100f * (float) pairOfQsGanador / (float) pairOfQs));
    }
}

