package poker.listeners;

import poker.EventListener;
import poker.Holdem;
import poker.Player;
import poker.juegos.Juego;

import java.util.HashMap;
import java.util.Map;

public class JuegosGanadores implements EventListener {

    private int games = 0;
    private Map<Juego.Tipo, Integer> contadores = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (event.equals(HoldemEvents.NEW_GAME)) {
            games++;
        } else if (event.equals(HoldemEvents.FINISHED)) {
            for (Player ganador :
                    holdem.getGanadores()) {
                contadores.compute(ganador.getJuego().getType(), (k, v) -> v == null ? 1 : v + 1);
                break;
            }
        }
    }

    @Override
    public void printStatics() {
        if (contadores.containsKey(Juego.Tipo.NADA)) {
            System.out.println("% de veces que el juego ganador es NADA = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.NADA) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.PAR_SIMPLE)) {
            System.out.println("% de veces que el juego ganador es PAR_SIMPLE = \t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.PAR_SIMPLE) / (float) games));
        }

        if (contadores.containsKey(Juego.Tipo.PAR_DOBLE)) {
            System.out.println("% de veces que el juego ganador es PAR_DOBLE = \t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.PAR_DOBLE) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.PIERNA)) {
            System.out.println("% de veces que el juego ganador es PIERNA = \t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.PIERNA) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.ESCALERA)) {
            System.out.println("% de veces que el juego ganador es ESCALERA = \t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.ESCALERA) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.COLOR)) {
            System.out.println("% de veces que el juego ganador es COLOR = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.COLOR) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.FULL)) {
            System.out.println("% de veces que el juego ganador es FULL = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.FULL) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.POKER)) {
            System.out.println("% de veces que el juego ganador es POKER = \t\t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.POKER) / (float) games));
        }
        if (contadores.containsKey(Juego.Tipo.ESCALERA_COLOR)) {
            System.out.println("% de veces que el juego ganador es ESCALERA_COLOR = \t" + String.format("%3.2f%%", 100f * (float) contadores.get(Juego.Tipo.ESCALERA_COLOR) / (float) games));
        }

        System.out.println("Totalidad de juegos ganadores = \t\t\t\t\t\t" + String.format("%3.2f%%", 100f * (float) contadores.values().stream().mapToInt(Integer::intValue).sum() / (float) games));
    }
}
