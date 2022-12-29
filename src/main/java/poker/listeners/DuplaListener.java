package poker.listeners;

import poker.Card;
import poker.Holdem;
import poker.Player;
import poker.simulations.Dupla;
import poker.simulations.TipoCombinacion;

import java.util.*;

public class DuplaListener implements HoldemStatics {

    private Map<Dupla, Integer> contadoresJuego = new HashMap<>();
    private Map<Dupla, Integer> contadoresJuegoGanador = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.FINISHED)) {
            Set<Dupla> dupleSet = new HashSet<>();
            for (Player player: holdem.getPlayers() ) {
                Card[] cards = player.getCards().toArray(new Card[2]);
                Dupla dupla = new Dupla(cards[0], cards[1]);
                // Si el juego es ganador evitamos contarlo mas de una vez
                if(!dupleSet.contains(dupla)) {
                    contadoresJuego.compute(dupla, (k, v) -> v == null ? 1 : v + 1);
                    if (holdem.getGanadores().contains(player)) {
                        contadoresJuegoGanador.compute(dupla, (k, v) -> v == null ? 1 : v + 1);
                    }
                    dupleSet.add(dupla);
                }
            }
        }
    }

    @Override
    public void printStatics() {
        contadoresJuegoGanador.entrySet().stream().sorted( Comparator.comparingDouble(entry -> (double) entry.getValue() / contadoresJuego.get(entry.getKey())) ) .forEach( entry ->{
            Dupla dupla = entry.getKey();
            int contadorGanador = entry.getValue();
            int contadorJuego = contadoresJuego.get(dupla);
            System.out.printf("Probabilidad de ganar con %s  = \t\t\t %3.2f%% \n",  dupla.toString(), 100f * (float) contadorGanador / (float) contadorJuego);
        });
    }

}
