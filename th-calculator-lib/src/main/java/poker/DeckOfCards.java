package poker;

import java.util.*;

public class DeckOfCards {
    private final Card[] cards = Card.values();
    private final Set<Integer> repartidas = new HashSet<>();
    private final Set<Integer> cardsToAvoid = new HashSet<>();

    private PrimitiveIterator.OfInt randomIterator = new Random().ints(0, 52).iterator();

    public void reset() {
        repartidas.clear();
    }

    public void addCardsToAvoid(Set<Card> cardsToRemove) {
        cardsToRemove.forEach(card -> {
            this.cardsToAvoid.add(getCardIdx(card));
        });
    }

    public Card getRandomCard() {
        if (repartidas.size() + cardsToAvoid.size() == 52) {
            throw new RuntimeException("Todas las cartas fueron repartidas");
        }

        Integer index = null;
        do {
            index = randomIterator.nextInt();
        } while (repartidas.contains(index) || cardsToAvoid.contains(index));

        repartidas.add(index);

        return cards[index];
    }

    private int getCardIdx(Card theCardToRemove) {
        for (int idx = 0; idx < 52; idx++) {
            if (cards[idx].equals(theCardToRemove)) {
                repartidas.add(idx);
                return idx;
            }
        }
        throw new RuntimeException("La carta " + theCardToRemove + " ya fué repartida.");
    }

}
