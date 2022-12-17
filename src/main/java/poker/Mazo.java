package poker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mazo {
    private final Card[] cards =  Card.values();
    private final Set<Integer> repartidas = new HashSet<>();
    private final Random rnd = new Random();
    public void reset() {
        repartidas.clear();
    }

    public Card getRandomCard() {
        if (repartidas.size() == 52) {
            throw new RuntimeException("Todas las cartas se repartieron");
        }

        Integer index = null;
        do {
            index = rnd.nextInt(52);
        } while (repartidas.contains(index));

        repartidas.add(index);

        return cards[index];
    }

    public Card markRepartida(Card theCardToRemove) {
        for (int idx = 0; idx < 52; idx++) {
            if (cards[idx].equals(theCardToRemove)) {
                if (repartidas.contains(idx)) {
                    throw new RuntimeException("La carta " + theCardToRemove + " ya fué repartida.");
                }
                repartidas.add(idx);
                return theCardToRemove;
            }
        }
        throw new RuntimeException("La carta " + theCardToRemove + " no fue encontrada.");
    }

    public void removeCards(Collection<Card> cardsToRemove) {
        for (Card theCardToRemove : cardsToRemove) {
            markRepartida(theCardToRemove);
        }
    }

}
