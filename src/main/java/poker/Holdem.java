package poker;

import poker.juegos.Juego;

import java.util.*;

import static poker.EventListener.HoldemEvents.*;

public class Holdem {
    private final List<EventListener> listeners = new ArrayList<>();
    private final DeckOfCards deckOfCards = new DeckOfCards();
    private final Set<Player> ganadores = new HashSet<>();
    private final List<Player> players;
    private final CommunityCards communityCards;
    private int totalPot = 0;

    public Holdem(List<Player> players, CommunityCards communityCards) {
        this.players = players;
        this.communityCards = communityCards;

        this.communityCards.collectCardsToAvoid(this.deckOfCards);

        this.players.forEach(player -> {
            player.collectCardsToAvoid(this.deckOfCards);
        });
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public Set<Player> getGanadores() {
        return ganadores;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void reset() {
        // Reset
        totalPot = 0;
        ganadores.clear();
        deckOfCards.reset();
        communityCards.reset();
        players.forEach(player -> player.reset());
    }

    public Set<Player> play() {
        triggerEvent(EventListener.HoldemEvents.NEW_GAME);

        //Comienza el partido
        List<Player> playersInGame = new ArrayList<>(players);

        // Repartir las cartas aleatorias
        communityCards.receiveRandomCards(deckOfCards);
        playersInGame.forEach(player -> player.receiveRandomCards(deckOfCards));

        if (communityCards.getCards().size() != 5) {
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
        communityCards.showFlop();
        triggerEvent(FLOP);

        bet(FLOP, playersInGame);

        // Mostrar TURN
        communityCards.showTurn();
        triggerEvent(TURN);

        bet(TURN, playersInGame);

        // Mostrar RIVER
        communityCards.showRiver();
        triggerEvent(RIVER);

        bet(RIVER, playersInGame);


        // Calcular ganadores
        ganadores.addAll(calcularGanadores(playersInGame, communityCards));

        // Repartir pozo
        repartirPot(ganadores);

        triggerEvent(FINISHED);

        return ganadores;
    }


    private void bet(EventListener.HoldemEvents stage, List<Player> playersInGame) {
        ListIterator<Player> iterator = playersInGame.listIterator();

        int callPoints = 0;
        switch (stage) {
            case CARTAS_REPARETIDAS:
                //callPoints = 1;
                callPoints = 0;
                break;
            case FLOP:
                //callPoints = 2;
                callPoints = 0;
                break;
            case TURN:
                //callPoints = 3;
                callPoints = 0;
                break;
            case RIVER:
                //callPoints = 5;
                callPoints = 1;
                break;
        }

        int currentPot = 0;
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.call(stage, playersInGame.size(), communityCards)) {
                player.decreasePoints(callPoints);
                currentPot += callPoints;
            } else {
                iterator.remove();
            }
        }

        this.totalPot += currentPot;
    }

    private void repartirPot(Set<Player> ganadores) {
        ganadores.forEach(ganador -> ganador.increasePoints(totalPot / ganadores.size()));

        int reminder = totalPot % ganadores.size();

        if (reminder > 0) {
            ganadores.stream().sorted(Comparator.comparingInt(Player::getPoints)).limit(reminder).forEach(player -> player.increasePoints(1));
        }
    }

    public static Set<Player> calcularGanadores(List<Player> playerCards, CommunityCards communityCards) {
        Set<Player> ganadores = new HashSet<>();
        Juego maxJuego = null;
        for (Player player : playerCards) {
            Juego juego = player.calcularJuego(communityCards);
            if (maxJuego == null) {
                ganadores.add(player);
                maxJuego = juego;
            } else {
                int compare = juego.compareTo(maxJuego);
                if (compare > 0) {
                    ganadores.clear();
                    ganadores.add(player);
                    maxJuego = juego;
                } else if (compare == 0) {
                    ganadores.add(player);
                }
            }
        }
        return ganadores;
    }

    private void triggerEvent(EventListener.HoldemEvents event) {
        for (EventListener listener : listeners) {
            listener.catchEvent(event, this);
        }
    }

    public CommunityCards getCommonCards() {
        return communityCards;
    }
}
