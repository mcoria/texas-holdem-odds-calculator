package poker.reports.listeners;

import poker.Card;
import poker.Holdem;
import poker.Player;
import poker.analysis.PocketCardsGrouping;

import java.util.*;

public class PocketCardsGroupingListener implements HoldemStatics {

    private Map<PocketCardsGrouping, Integer> contadoresJuego = new HashMap<>();
    private Map<PocketCardsGrouping, Integer> contadoresJuegoGanador = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvent event, Holdem holdem) {
        if (event.equals(HoldemEvent.FINISHED)) {
            Set<PocketCardsGrouping> dupleSet = new HashSet<>();
            for (Player player : holdem.getPlayers()) {
                Card[] cards = player.getPocketCards().toArray(new Card[2]);
                PocketCardsGrouping pocketCardsGrouping = new PocketCardsGrouping(cards[0], cards[1]);
                // Si el juego es ganador evitamos contarlo mas de una vez
                if (!dupleSet.contains(pocketCardsGrouping)) {
                    contadoresJuego.compute(pocketCardsGrouping, (k, v) -> v == null ? 1 : v + 1);
                    if (holdem.getGanadores().contains(player)) {
                        contadoresJuegoGanador.compute(pocketCardsGrouping, (k, v) -> v == null ? 1 : v + 1);
                    }
                    dupleSet.add(pocketCardsGrouping);
                }
            }
        }
    }

    @Override
    public void printStatics() {
        contadoresJuegoGanador.entrySet().stream().sorted(Comparator.comparingDouble(entry -> (double) entry.getValue() / contadoresJuego.get(entry.getKey()))).forEach(entry -> {
            PocketCardsGrouping pocketCardsGrouping = entry.getKey();
            int contadorGanador = entry.getValue();
            int contadorJuego = contadoresJuego.get(pocketCardsGrouping);
            System.out.printf("Probabilidad de ganar con %s  = \t\t\t %3.2f%% \n", pocketCardsGrouping.toString(), 100f * (float) contadorGanador / (float) contadorJuego);
        });
    }

}
