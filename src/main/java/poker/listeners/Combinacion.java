package poker.listeners;

import poker.Holdem;
import poker.Player;
import poker.simulations.TipoCombinacion;

import java.util.HashMap;
import java.util.Map;

public class Combinacion implements HoldemStatics {

    private Map<TipoCombinacion, Integer> contadorJuego = new HashMap<>();
    private Map<TipoCombinacion, Integer> contadorJuegoGanador = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.FINISHED)) {
            for (Player player: holdem.getPlayers() ) {
                TipoCombinacion combinacion = TipoCombinacion.loadTipo(player.getCards());

                contadorJuego.compute(combinacion, (k, v) -> v == null ? 1 : v + 1);
                if (holdem.getGanadores().contains(player)) {
                    contadorJuegoGanador.compute(combinacion, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }
    }

    @Override
    public void printStatics() {
        for (TipoCombinacion combinacion :
                TipoCombinacion.values()) {
            if (contadorJuego.containsKey(combinacion)) {
                int contador = contadorJuego.get(combinacion);
                int contadorGanador = contadorJuegoGanador.getOrDefault(combinacion, 0);
                System.out.println("Probabilidad de ganar con '" + combinacion.getDescription() + "' = \t\t\t" + String.format("%3.2f%%", 100f * (float) contadorGanador / (float) contador));
            }
        }
    }

}
