package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
    protected final Set<Card> pocketCards = new HashSet<>();
    private Juego juego;
    protected boolean defaultCallResponse = true;
    private int points = 0;

    public abstract void receiveRandomCards(DeckOfCards deckOfCards);
    public abstract void collectCardsToAvoid(DeckOfCards deckOfCards);
    protected abstract void pocketCardsReset();

    public Set<Card> getPocketCards() {
        return pocketCards;
    }

    public void reset() {
        juego = null;
        pocketCardsReset();
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

    public boolean call(HoldemListener.HoldemEvents stage, int playersInGame, CommunityCards communityCards) {
        return this.defaultCallResponse;
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

    public String getPlayerName(){
        return getClass().getSimpleName();
    }

}

