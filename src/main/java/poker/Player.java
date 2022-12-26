package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class Player {
    private final Set<Card> cards = new HashSet<>();
    private Juego juego = null;
    private BiPredicate<Player, EventListener.HoldemEvents> callPredicate;
    private boolean clearCardsOnRest = true;

    public Player() {
        setCallPredicate((p, e) -> true);
    }

    public void receiveRandomCards(Mazo mazo) {
        if (cards.size() == 0) {
            setCards(mazo.getRandomCard(), mazo.getRandomCard());
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void reset() {
        juego = null;
        if (clearCardsOnRest) {
            cards.clear();
        }
    }

    public Juego getJuego() {
        if (juego == null) {
            throw new RuntimeException("Juego no fue calculado");
        }
        return juego;
    }

    public Juego calcularJuego(CommonCards commonCards) {
        Set<Card> theCards = new HashSet<>();
        theCards.addAll(commonCards.getCards());
        theCards.addAll(cards);
        juego = Juego.cargarJuego(theCards);
        return juego;
    }

    public Player setCards(Card card1, Card card2) {
        if (cards.size() != 0) {
            throw new RuntimeException("Las cartas ya fueron recibidas.");
        }

        cards.add(card1);
        cards.add(card2);

        return this;
    }

    public Player setClearCardsOnRest(boolean clearCardsOnRest) {
        this.clearCardsOnRest = clearCardsOnRest;
        return this;
    }

    public boolean call(EventListener.HoldemEvents stage) {
        return callPredicate.test(this, stage);
    }

    public Player setCallPredicate(BiPredicate<Player, EventListener.HoldemEvents> callPredicate) {
        this.callPredicate = callPredicate;
        return this;
    }
}

