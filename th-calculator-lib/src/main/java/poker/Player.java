package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final Set<Card> pocketCards = new HashSet<>();
    private Juego juego;
    private CardBucketStrategy cardBucketStrategy = new DefaultCardBucketStrategy();
    protected boolean defaultCallResponse = true;

    private int points = 0;

    public void receiveRandomCards(DeckOfCards deckOfCards) {
        cardBucketStrategy.receiveRandomCards(deckOfCards);
    }

    public Set<Card> getPocketCards() {
        return pocketCards;
    }

    public void reset() {
        juego = null;
        cardBucketStrategy.reset();
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
        theCards.addAll(pocketCards);
        juego = Juego.cargarJuego(theCards);
        return juego;
    }

    public Player setPocketCards(Card card1, Card card2) {
        cardBucketStrategy = new NoOpCardBucketStrategy();

        pocketCards.clear();
        pocketCards.add(card1);
        pocketCards.add(card2);

        return this;
    }

    public boolean call(HoldemListener.HoldemEvents stage, int playersInGame, CommunityCards communityCards) {
        return this.defaultCallResponse;
    }

    public void collectCardsToAvoid(DeckOfCards deckOfCards) {
        cardBucketStrategy.collectCardsToAvoid(deckOfCards);
    }

    public Player setDefaultCallResponse(boolean defaultCallResponse) {
        this.defaultCallResponse = defaultCallResponse;
        return this;
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

    private interface CardBucketStrategy {

        void receiveRandomCards(DeckOfCards deckOfCards);

        void reset();

        void collectCardsToAvoid(DeckOfCards deckOfCards);
    }

    private class DefaultCardBucketStrategy implements CardBucketStrategy {

        @Override
        public void receiveRandomCards(DeckOfCards deckOfCards) {
            pocketCards.add(deckOfCards.getRandomCard());
            pocketCards.add(deckOfCards.getRandomCard());
        }

        @Override
        public void reset() {
            pocketCards.clear();
        }

        @Override
        public void collectCardsToAvoid(DeckOfCards deckOfCards) {
        }
    }

    private class NoOpCardBucketStrategy implements CardBucketStrategy {
        @Override
        public void receiveRandomCards(DeckOfCards deckOfCards) {
        }

        @Override
        public void reset() {
        }

        @Override
        public void collectCardsToAvoid(DeckOfCards deckOfCards) {
            deckOfCards.addCardsToAvoid(pocketCards);
        }
    }

    public String getPlayerName(){
        return getClass().getSimpleName();
    }

}

