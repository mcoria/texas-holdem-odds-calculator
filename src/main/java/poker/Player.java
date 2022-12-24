package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class Player {
    private final Set<Card> cards = new HashSet<>();
    private Juego juego = null;

    private boolean callResponse = true;


    public boolean call(EventListener.HoldemEvents stage) {
        return callResponse;
    }

    public void setCards() {
    }

    public Player setCallResponse(boolean callResponse) {
        this.callResponse = callResponse;
        return this;
    }

    public void receiveRandomCards(Mazo mazo) {
        if (cards.size() == 0) {
            setCards(mazo.getRandomCard(), mazo.getRandomCard());
        } else if (cards.size() == 1) {
            cards.add(mazo.getRandomCard());
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void reset() {
        juego = null;
        cards.clear();
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

    protected void setCards(Card card1, Card card2) {
        if(cards.size() != 0){
            throw new RuntimeException("Las cartas ya fueron recibidas.");
        }
        cards.add(card1);
        cards.add(card2);
    }
}
