package poker;

import org.junit.Test;
import poker.simulations.PocketCardsGrouping;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PocketCardsGroupingTest {

    @Test
    public void combinar(){
        Map<PocketCardsGrouping, Integer> duplaCounters = new HashMap<>();
        Card[] cardArray = Card.values();
        int counter = 0;
        for (int i = 0; i < 51; i++) {
            Card card1 = cardArray[i];
            for (int j = i + 1; j < 52; j++) {
                Card card2 = cardArray[j];

                PocketCardsGrouping pocketCardsGrouping = new PocketCardsGrouping(card1, card2);
                duplaCounters.compute(pocketCardsGrouping, (k, v) -> v == null ? 1 : v + 1);

                counter++;
            }
        }
        System.out.printf("Combinaciones posibles %d \n", counter);

        assertEquals(1326, counter);

        for (Map.Entry<PocketCardsGrouping, Integer> duplaCounter:
                duplaCounters.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toList()) ) {
            PocketCardsGrouping pocketCardsGrouping = duplaCounter.getKey();
            int contador = duplaCounter.getValue();
            System.out.printf("Combinacion '%s'\t\t%3d\t\t%3.2f%%\n",  pocketCardsGrouping.toString(), contador, 100f * (float) contador / (float) counter);
            if(pocketCardsGrouping.isPair()){
                assertEquals(6, contador);
            }
        }

    }
}
