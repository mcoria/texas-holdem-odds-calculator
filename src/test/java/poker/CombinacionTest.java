package poker;

import org.junit.Test;
import poker.simulations.Dupla;
import poker.simulations.TipoCombinacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CombinacionTest {

    @Test
    public void combinar(){
        //Map<TipoCombinacion, Integer> combinatoria = new HashMap<>();
        Map<Dupla, Integer> duplaCounters = new HashMap<>();
        Card[] cardArray = Card.values();
        int counter = 0;
        for (int i = 0; i < 51; i++) {
            Card card1 = cardArray[i];
            for (int j = i + 1; j < 52; j++) {
                Card card2 = cardArray[j];

                //TipoCombinacion combinacion = TipoCombinacion.loadTipo(Set.of(card1, card2));
                // combinatoria.compute(combinacion, (k, v) -> v == null ? 1 : v + 1);

                Dupla dupla = new Dupla(card1, card2);
                duplaCounters.compute(dupla, (k, v) -> v == null ? 1 : v + 1);

                counter++;
            }
        }
        System.out.printf("Combinaciones posibles %d \n", counter);

        /*
        for (TipoCombinacion combinacion :
                TipoCombinacion.values()) {
            if (combinatoria.containsKey(combinacion)) {
                int contador = combinatoria.get(combinacion);
                System.out.printf("Combinacion '%s' \t\t %d \t %3.2f%% \n", combinacion.getDescription(), contador, 100f * (float) contador / (float) counter);
            }
        }
         */

        for (Map.Entry<Dupla, Integer> duplaCounter:
                duplaCounters.entrySet()) {
            Dupla dupla = duplaCounter.getKey();
            int contador = duplaCounter.getValue();
            System.out.printf("Combinacion '%s' \t\t %d \t %3.2f%% \n",  dupla.toString(), contador, 100f * (float) contador / (float) counter);
        }

    }
}
