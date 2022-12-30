package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final Set<Card> cards = new HashSet<>();
    private Juego juego;
    private CardBucket cardBucket = new DefaultCardBucket();
    private boolean defaultCallResponse = true;

    private int points = 0;

    public void receiveRandomCards(DeckOfCards deckOfCards) {
        cardBucket.receiveRandomCards(deckOfCards);
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

    public Juego calcularJuego(CommunityCards communityCards) {
        Set<Card> theCards = new HashSet<>();
        theCards.addAll(communityCards.getCards());
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

    public boolean call(EventListener.HoldemEvents stage, int playersInGame, CommunityCards communityCards) {
        return this.defaultCallResponse;
    }

    public void collectCardsToAvoid(DeckOfCards deckOfCards) {
        cardBucket.collectCardsToAvoid(deckOfCards);
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

        void receiveRandomCards(DeckOfCards deckOfCards);

        void reset();

        void collectCardsToAvoid(DeckOfCards deckOfCards);
    }

    private class DefaultCardBucket implements CardBucket {

        @Override
        public void receiveRandomCards(DeckOfCards deckOfCards) {
            cards.add(deckOfCards.getRandomCard());
            cards.add(deckOfCards.getRandomCard());
        }

        @Override
        public void reset() {
            cards.clear();
        }

        @Override
        public void collectCardsToAvoid(DeckOfCards deckOfCards) {
        }
    }

    private class NoOpCardBucket implements CardBucket {
        @Override
        public void receiveRandomCards(DeckOfCards deckOfCards) {
        }

        @Override
        public void reset() {
        }

        @Override
        public void collectCardsToAvoid(DeckOfCards deckOfCards) {
            deckOfCards.addCardsToAvoid(cards);
        }
    }


}

