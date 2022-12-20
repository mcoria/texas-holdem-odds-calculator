package poker;

import poker.juegos.Juego;

import java.util.*;

import static poker.EventListener.HoldemEvents.*;

public class Holdem {
    private final List<EventListener> listeners = new ArrayList<>();
    private final Mazo mazo = new Mazo();
    private final Set<Player> ganadores = new HashSet<>();
    private final List<Player> players;
    private final CommonCards commonCards;

    public Holdem(List<Player> players, CommonCards commonCards) {
        this.players = players;
        this.commonCards = commonCards;
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public Set<Player> getGanadores() {
        return ganadores;
    }

    public Set<Player> play() {
        // Reset
        ganadores.clear();
        mazo.reset();
        commonCards.reset();
        players.forEach(player -> player.reset());
        triggerEvent(EventListener.HoldemEvents.NEW_GAME);

        List<Player> playersInGame = new ArrayList<>(players);

        // Repartir las cartas
        commonCards.receiveCards(mazo);
        mazo.removeCards(commonCards.getCards());
        playersInGame.forEach(player -> player.receiveCards());
        playersInGame.forEach(player-> mazo.removeCards(player.getCards()));

        commonCards.receiveRandomCards(mazo);
        playersInGame.forEach(player -> player.receiveRandomCards(mazo));


        if (commonCards.getCards().size() != 5) {
            throw new RuntimeException("Common cards have not been set");
        }
        for (Player playerCard : players) {
            if (playerCard.getCards().size() != 2) {
                throw new RuntimeException("Player cards have not been set");
            }
        }
        triggerEvent(CARTAS_REPARETIDAS);

        bet(CARTAS_REPARETIDAS, playersInGame);

        // Mostrar FLOW
        commonCards.showFlop();
        triggerEvent(FLOP);

        bet(FLOP, playersInGame);

        // Mostrar TURN
        commonCards.showTurn();
        triggerEvent(TURN);

        bet(TURN, playersInGame);

        // Mostrar RIVER
        commonCards.showRiver();
        triggerEvent(RIVER);

        bet(RIVER, playersInGame);

        // Mostrar las cartas para calcular ganadores
        ganadores.addAll(calcularGanadores(playersInGame, commonCards));
        triggerEvent(FINISHED);

        //collect();
        return ganadores;
    }

    private void bet(EventListener.HoldemEvents stage, List<Player> playersInGame) {
        ListIterator<Player> iterator = playersInGame.listIterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            boolean call = false;
            switch (stage) {
                case CARTAS_REPARETIDAS:
                    call = player.call_CARTAS_REPARETIDAS();
                    break;
                case FLOP:
                    call = player.call_FLOP();
                    break;
                case TURN:
                    call = player.call_TURN();
                    break;
                case RIVER:
                    call = player.call_RIVER();
                    break;
            }
            if (!call) {
                iterator.remove();
            }
        }
    }

    public static Set<Player> calcularGanadores(List<Player> playerCards, CommonCards commonCards) {
        Set<Player> ganadores = new HashSet<>();
        Juego maxJuego = null;
        for (Player player : playerCards) {
            Juego juego = player.calcularJuego(commonCards);
            if (maxJuego == null) {
                ganadores.add(player);
                maxJuego = juego;
            } else if (juego.compareTo(maxJuego) > 0) {
                ganadores.clear();
                ganadores.add(player);
                maxJuego = juego;
            } else if (juego.compareTo(maxJuego) == 0) {
                ganadores.add(player);
            }
        }
        return ganadores;
    }

    private void triggerEvent(EventListener.HoldemEvents event) {
        for (EventListener listener : listeners) {
            listener.raiseEvent(event, this);
        }
    }
}
