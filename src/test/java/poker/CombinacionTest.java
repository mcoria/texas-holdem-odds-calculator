package poker;

import org.junit.Test;
import poker.simulations.PocketCards;

import java.util.HashMap;
import java.util.Map;

public class CombinacionTest {

    @Test
    public void combinar(){
        Map<PocketCards, Integer> duplaCounters = new HashMap<>();
        Card[] cardArray = Card.values();
        int counter = 0;
        for (int i = 0; i < 51; i++) {
            Card card1 = cardArray[i];
            for (int j = i + 1; j < 52; j++) {
                Card card2 = cardArray[j];

                PocketCards pocketCards = new PocketCards(card1, card2);
                duplaCounters.compute(pocketCards, (k, v) -> v == null ? 1 : v + 1);

                counter++;
            }
        }
        System.out.printf("Combinaciones posibles %d \n", counter);


        for (Map.Entry<PocketCards, Integer> duplaCounter:
                duplaCounters.entrySet()) {
            PocketCards pocketCards = duplaCounter.getKey();
            int contador = duplaCounter.getValue();
            System.out.printf("Combinacion '%s'\t\t%3d\t\t%3.2f%%\n",  pocketCards.toString(), contador, 100f * (float) contador / (float) counter);
        }

    }
}
