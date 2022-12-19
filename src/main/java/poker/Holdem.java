package poker;

import poker.juegos.Juego;
import poker.repartirstrategies.RepartirStrategy;

import java.util.*;

import static poker.EventListener.HoldemEvents.*;

public class Holdem {
    private final List<EventListener> listeners = new ArrayList<>();
    private final CommonCards commonCards = new CommonCards();
    private final List<Player> playerCards = new ArrayList<>();
    private final Mazo mazo = new Mazo();
    private final RepartirStrategy repartirStrategy;
    private Set<Player> ganadores;

    public Holdem(int playersCount, RepartirStrategy repartirStrategy) {
        for (int i = 0; i < playersCount; i++) {
            playerCards.add(new Player());
        }
        this.repartirStrategy = repartirStrategy;
    }

    public Player getPlayer(int playerNumber) {
        return playerCards.get(playerNumber);
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public Set<Player> getGanadores() {
        return ganadores;
    }

    public Set<Player> play() {
        // Reset
        ganadores = null;
        mazo.reset();
        commonCards.reset();
        playerCards.forEach(player -> player.reset());
        triggerEvent(EventListener.HoldemEvents.NEW_GAME);

        List<Player> playersInGame = new ArrayList<>(playerCards);

        // Repartir las cartas
        repartirStrategy.apply(mazo, commonCards, playerCards);
        if(commonCards.getCards().size() != 5){
            throw new RuntimeException("Common cards have not been set");
        }
        for (Player playerCard: playerCards) {
            if(playerCard.getCards().size() != 2){
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
        ganadores = calcularGanadores(playersInGame);
        triggerEvent(FINISHED);

        //collect();
        return ganadores;
    }

    private void bet(EventListener.HoldemEvents stage, List<Player> playersInGame) {
        ListIterator<Player> iterator = playersInGame.listIterator();
        while (iterator.hasNext()){
            Player player = iterator.next();
            boolean call = false;
            switch (stage){
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
            if( !call ) {
                iterator.remove();
            }
        }
    }

    protected Set<Player> calcularGanadores(List<Player> playerCards) {
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
