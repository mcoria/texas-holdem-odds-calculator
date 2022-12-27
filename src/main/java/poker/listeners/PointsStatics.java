package poker.listeners;

import poker.EventListener;
import poker.Holdem;
import poker.Player;
import poker.juegos.Juego;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;

public class PointsStatics implements HoldemStatics {

    int games = 0;
    int empates = 0;

    private Map<Player, IntSummaryStatistics> pointStatics = new HashMap<>();
    private Map<Player, Integer> contadorGanador = new HashMap<>();

    @Override
    public void catchEvent(HoldemEvents event, Holdem holdem) {
        if (HoldemEvents.NEW_GAME.equals(event)) {
            games++;
        } else if (HoldemEvents.FINISHED.equals(event)) {
            for (Player player :
                    holdem.getPlayers()) {
                IntSummaryStatistics playerStatic = pointStatics.computeIfAbsent(player, k -> new IntSummaryStatistics());
                playerStatic.accept(player.getPoints());
            }

            if(holdem.getGanadores().size() > 1) {
                empates++;
            }

            for (Player player :
                    holdem.getGanadores()) {
                contadorGanador.compute(player, (k,v) -> v == null ? 1 : v + 1);
            }
        }
    }

    @Override
    public void printStatics() {
        System.out.println("Cantidad de empates = \t" + String.format("%d", empates ));
        System.out.println("% de empates = \t" + String.format("%3.2f%%", 100f * (float) empates / (float) games));
        pointStatics.entrySet().stream().forEach( entry -> {
            Player player = entry.getKey();
            IntSummaryStatistics playerStatic = entry.getValue();
            Integer juegosGanados = contadorGanador.getOrDefault(player, 0);
            System.out.printf ("Player partidos ganados = %d, points = %d, min = %d, max = %d, avg = %f \n", juegosGanados, player.getPoints(), playerStatic.getMin(),  playerStatic.getMax(), playerStatic.getAverage());
        });
    }
}


