package poker;

import org.junit.Test;
import poker.simulations.Dupla;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CombinacionTest {

    @Test
    public void combinar(){
        Map<Dupla, Integer> duplaCounters = new HashMap<>();
        Card[] cardArray = Card.values();
        int counter = 0;
        for (int i = 0; i < 51; i++) {
            Card card1 = cardArray[i];
            for (int j = i + 1; j < 52; j++) {
                Card card2 = cardArray[j];

                Dupla dupla = new Dupla(card1, card2);
                duplaCounters.compute(dupla, (k, v) -> v == null ? 1 : v + 1);

                counter++;
            }
        }
        System.out.printf("Combinaciones posibles %d \n", counter);


        for (Map.Entry<Dupla, Integer> duplaCounter:
                duplaCounters.entrySet()) {
            Dupla dupla = duplaCounter.getKey();
            int contador = duplaCounter.getValue();
            System.out.printf("Combinacion '%s' \t\t %d \t %3.2f%% \n",  dupla.toString(), contador, 100f * (float) contador / (float) counter);
        }

    }
}
