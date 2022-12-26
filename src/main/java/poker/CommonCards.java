package poker;

import java.util.HashSet;
import java.util.Set;

public class CommonCards {
    private final Set<Card> cards = new HashSet<>();
    private Card flopCard1 = null;
    private Card flopCard2 = null;
    private Card flopCard3 = null;
    private Card turnCard = null;
    private Card riverCard = null;

    public void receiveRandomCards(Mazo mazo) {
        while (cards.size() < 5) {
            cards.add(mazo.getRandomCard());
        }
    }

    public CommonCards setFlop(Card card1, Card card2, Card card3) {
        flopCard1 = card1;
        flopCard2 = card2;
        flopCard3 = card3;

        cards.add(flopCard1);
        cards.add(flopCard2);
        cards.add(flopCard3);

        return this;
    }

    public CommonCards setTurn(Card card) {
        turnCard = card;
        cards.add(turnCard);
        return this;
    }

    public CommonCards setRiver(Card card) {
        riverCard = card;
        cards.add(riverCard);
        return this;
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
        if (flopCard1 != null && flopCard2 != null && flopCard3 != null) {
            cards.add(flopCard1);
            cards.add(flopCard2);
            cards.add(flopCard3);
        }
        if (turnCard != null) {
            cards.add(turnCard);
        }
        if (riverCard != null) {
            cards.add(riverCard);
        }
    }

    public void collectCardsToAvoid(Mazo mazo) {
        if (flopCard1 != null && flopCard2 != null && flopCard3 != null) {
            mazo.addCardsToAvoid(Set.of(flopCard1, flopCard2, flopCard3));
        }
        if (turnCard != null) {
            mazo.addCardsToAvoid(Set.of(turnCard));
        }
        if (riverCard != null) {
            mazo.addCardsToAvoid(Set.of(riverCard));
        }
    }
}
