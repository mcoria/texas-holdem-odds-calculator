package poker;

import java.util.HashSet;
import java.util.Set;

public class CommonCards {
    private final Set<Card> cards = new HashSet<>();

    public void receiveCards(Mazo mazo) {
        if (cards.size() != 0) {
            throw new RuntimeException("reset operation has not been invoked.");
        }
        cards.add(mazo.getRandomCard());
        cards.add(mazo.getRandomCard());
        cards.add(mazo.getRandomCard());
        cards.add(mazo.getRandomCard());
        cards.add(mazo.getRandomCard());
    }

    public void setCards(Card card1, Card card2, Card card3, Card card4, Card card5) {
        if (cards.size() != 0) {
            throw new RuntimeException("reset operation has not been invoked.");
        }
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
    }

    public void setFlop(Card card1, Card card2, Card card3) {
        if (cards.size() != 0) {
            throw new RuntimeException("reset operation has not been invoked.");
        }
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
    }

    public void setTurn(Card card) {
        if (cards.size() != 3) {
            throw new RuntimeException("Flop has not been set.");
        }
        cards.add(card);
    }

    public void setRiver(Card card) {
        if (cards.size() != 4) {
            throw new RuntimeException("Turn has not been set.");
        }
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void showFlop() {
    }

    public void showTurn() {
    }

    public void showRiver() {
    }

    public void reset() {
        cards.clear();
    }

}
