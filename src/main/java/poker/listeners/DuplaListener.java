package poker.listeners;

import poker.Card;
import poker.Holdem;
import poker.Player;
import poker.simulations.PocketCards;

import java.util.*;

public class DuplaListener implements HoldemStatics {

    private Map<PocketCards, Integer> contadoresJuego = new HashMap<>();
    private Map<PocketCards, Integer> contadoresJuegoGanador = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.FINISHED)) {
            Set<PocketCards> dupleSet = new HashSet<>();
            for (Player player : holdem.getPlayers()) {
                Card[] cards = player.getCards().toArray(new Card[2]);
                PocketCards pocketCards = new PocketCards(cards[0], cards[1]);
                // Si el juego es ganador evitamos contarlo mas de una vez
                if (!dupleSet.contains(pocketCards)) {
                    contadoresJuego.compute(pocketCards, (k, v) -> v == null ? 1 : v + 1);
                    if (holdem.getGanadores().contains(player)) {
                        contadoresJuegoGanador.compute(pocketCards, (k, v) -> v == null ? 1 : v + 1);
                    }
                    dupleSet.add(pocketCards);
                }
            }
        }
    }

    @Override
    public void printStatics() {
        contadoresJuegoGanador.entrySet().stream().sorted(Comparator.comparingDouble(entry -> (double) entry.getValue() / contadoresJuego.get(entry.getKey()))).forEach(entry -> {
            PocketCards pocketCards = entry.getKey();
            int contadorGanador = entry.getValue();
            int contadorJuego = contadoresJuego.get(pocketCards);
            System.out.printf("Probabilidad de ganar con %s  = \t\t\t %3.2f%% \n", pocketCards.toString(), 100f * (float) contadorGanador / (float) contadorJuego);
        });
    }

}
