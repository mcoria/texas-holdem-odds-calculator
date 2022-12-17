package poker;

import poker.juegos.Juego;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private final CommonCards commonCards;

    private Juego juego = null;

    private Set<Card> cards = new HashSet<>();

    public Player(CommonCards commonCards) {
        this.commonCards = commonCards;
    }

    public void receiveCards(Mazo mazo) {
        cards.add(mazo.getRandomCard());
        cards.add(mazo.getRandomCard());
    }

    public void setCards(Card card1, Card card2) {
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
            Set<Card> theCards = new HashSet<>();
            theCards.addAll(commonCards.getCards());
            theCards.addAll(cards);
            juego = Juego.cargarJuego(theCards);
        }
        return juego;
    }
}
