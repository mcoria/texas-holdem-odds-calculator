package poker;

import poker.juegos.Juego;
import poker.repartirstrategies.RepartirStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Holdem {
    private final List<EventListener> listeners = new ArrayList<>();
    private final CommonCards commonCards = new CommonCards();
    private final List<Player> playerCards = new ArrayList<>();
    private final Mazo mazo = new Mazo();
    private final RepartirStrategy repartirStrategy;
    private Set<Player> ganadores;

    public Holdem(int playersCount, RepartirStrategy repartirStrategy) {
        for (int i = 0; i < playersCount; i++) {
            playerCards.add(new Player(commonCards));
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
        ganadores = null;
        mazo.reset();
        commonCards.reset();
        playerCards.forEach(player -> player.reset());
        triggerEvent(EventListener.HoldemEvents.NEW_GAME);

        repartirStrategy.apply(mazo, commonCards, playerCards);
        if(commonCards.getCards().size() != 5){
            throw new RuntimeException("Common cards have not been set");
        }
        for (Player playerCard: playerCards) {
            if(playerCard.getCards().size() != 2){
                throw new RuntimeException("Player cards have not been set");
            }
        }

        triggerEvent(EventListener.HoldemEvents.CARTAS_REPARETIDAS);

        commonCards.showFlop();
        triggerEvent(EventListener.HoldemEvents.FLOP);

        commonCards.showTurn();
        triggerEvent(EventListener.HoldemEvents.CUARTA_CARTA);

        commonCards.showRiver();
        triggerEvent(EventListener.HoldemEvents.QUINTA_CARTA);

        ganadores = calcularGanadores(playerCards);
        triggerEvent(EventListener.HoldemEvents.FINISHED);
        return ganadores;
    }

    protected Set<Player> calcularGanadores(List<Player> playerCards) {
        Set<Player> ganadores = new HashSet<>();
        Juego maxJuego = null;
        for (Player player : playerCards) {
            Juego juego = player.getJuego();
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
