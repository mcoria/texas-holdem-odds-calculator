package poker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mazo {
    private final Card[] cards = Card.values();
    private final Set<Integer> repartidas = new HashSet<>();
    private final Set<Integer> cardsToAvoid = new HashSet<>();
    private final Random rnd = new Random();

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
            index = rnd.nextInt(52);
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
