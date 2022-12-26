package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class Player {
    private final Set<Card> cards = new HashSet<>();
    private Juego juego = null;
    private BiPredicate<Player, EventListener.HoldemEvents> callPredicate;
    private CardBucket cardBucket = new DefaultCardBucket();

    public Player() {
        setCallPredicate((p, e) -> true);
    }

    public void receiveRandomCards(Mazo mazo) {
        cardBucket.receiveRandomCards(mazo);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void reset() {
        juego = null;
        cardBucket.reset();
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
        cardBucket = new NoOpCardBucket();

        cards.clear();
        cards.add(card1);
        cards.add(card2);

        return this;
    }

    public boolean call(EventListener.HoldemEvents stage) {
        return callPredicate.test(this, stage);
    }

    public Player setCallPredicate(BiPredicate<Player, EventListener.HoldemEvents> callPredicate) {
        this.callPredicate = callPredicate;
        return this;
    }

    private interface CardBucket {

        void receiveRandomCards(Mazo mazo);

        void reset();
    }

    private class DefaultCardBucket implements CardBucket {

        @Override
        public void receiveRandomCards(Mazo mazo) {
            cards.add(mazo.getRandomCard());
            cards.add(mazo.getRandomCard());
        }

        @Override
        public void reset() {
            cards.clear();
        }
    }

    private class NoOpCardBucket implements CardBucket {

        @Override
        public void receiveRandomCards(Mazo mazo) {
        }

        @Override
        public void reset() {
        }

    }


}

