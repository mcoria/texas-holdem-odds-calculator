package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Juego juego = null;

    private Set<Card> cards = new HashSet<>();

    public void receiveCards() {
    }

    public void receiveRandomCards(Mazo mazo) {
        setCards(mazo.getRandomCard(), mazo.getRandomCard());
    }

    protected void setCards(Card card1, Card card2) {
        cards.add(card1);
        cards.add(card2);
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

    public Juego calcularJuego(CommonCards commonCards){
        Set<Card> theCards = new HashSet<>();
        theCards.addAll(commonCards.getCards());
        theCards.addAll(cards);
        juego = Juego.cargarJuego(theCards);
        return juego;
    }

    public boolean call_CARTAS_REPARETIDAS() {
        return true;
    }

    public boolean call_FLOP() {
        return true;
    }

    public boolean call_TURN() {
        return true;
    }

    public boolean call_RIVER() {
        return true;
    }

}
