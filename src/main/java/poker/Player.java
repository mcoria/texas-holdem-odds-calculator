package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;

public class Player {
    private final Set<Card> cards = new HashSet<>();
    private Juego juego;
    private CardBucket cardBucket = new DefaultCardBucket();
    private boolean defaultCallResponse = true;

    private int points = 0;

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

    public boolean call(EventListener.HoldemEvents stage, int playersInGame, CommonCards commonCards) {
        return this.defaultCallResponse;
    }

    public void collectCardsToAvoid(Mazo mazo) {
        cardBucket.collectCardsToAvoid(mazo);
    }

    public void setDefaultCallResponse(boolean defaultCallResponse) {
        this.defaultCallResponse = defaultCallResponse;
    }

    public void decreasePoints(int points) {
        this.points -= points;
    }

    public void increasePoints(int points) {
        this.points += points;
    }

    public int getPoints(){
        return this.points;
    }

    private interface CardBucket {

        void receiveRandomCards(Mazo mazo);

        void reset();

        void collectCardsToAvoid(Mazo mazo);
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

        @Override
        public void collectCardsToAvoid(Mazo mazo) {
        }
    }

    private class NoOpCardBucket implements CardBucket {
        @Override
        public void receiveRandomCards(Mazo mazo) {
        }

        @Override
        public void reset() {
        }

        @Override
        public void collectCardsToAvoid(Mazo mazo) {
            mazo.addCardsToAvoid(cards);
        }
    }


}

