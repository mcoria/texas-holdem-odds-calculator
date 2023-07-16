package poker.reports.listeners;

import poker.Holdem;
import poker.Player;
import poker.juegos.Juego;

import java.util.*;

public class GamesDebug implements HoldemStatics {
    @Override
    public void catchEvent(HoldemEvent event, Holdem holdem) {
        if (HoldemEvent.FINISHED.equals(event)) {
            System.out.println("--------------------------------------------");

            System.out.println("Common cards " + holdem.getCommunityCards());

            for (Player player :
                    holdem.getGanadores()) {
                System.out.println(player.getJuego() + "*");
            }

            List<Juego> juegos = new ArrayList<>();
            for (Player player :
                    holdem.getPlayers()) {
                if (!holdem.getGanadores().contains(player)) {
                    juegos.add(player.getJuego());
                }
            }
            Collections.sort(juegos, Collections.reverseOrder());

            for (Juego juego :
                    juegos) {
                System.out.println(juego);
            }

        }
    }

    @Override
    public void printStatics() {
    }
}
