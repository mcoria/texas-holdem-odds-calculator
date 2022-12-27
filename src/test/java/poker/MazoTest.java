package poker;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MazoTest {

    @Test
    public void testGetCard(){
        Mazo mazo = new Mazo();
        Set<Card> cartas= new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cartas.add(mazo.getRandomCard());
        }
        assertEquals(52, cartas.size());
    }

    @Test(expected = RuntimeException.class)
    public void testGetCardException(){
        Mazo mazo = new Mazo();
        Set<Card> cartas= new HashSet<>();
        for (int i = 0; i < 53; i++) {
            cartas.add(mazo.getRandomCard());
        }
    }
}
