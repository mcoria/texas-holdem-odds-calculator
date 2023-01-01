package poker.analysis;

import org.junit.Test;
import poker.Card;

import static org.junit.Assert.assertEquals;

public class OddsCalculatorTest {

    @Test
    public void testCalculator(){
        OddsCalculator calculator = new OddsCalculator();

        calculator.setNumberOfPlayers(2);

        calculator.setPocketCards(Card.CLUBS_ACE, Card.DIAMONDS_ACE);

        float odds = calculator.calculateOds();

        assertEquals(85f, odds * 100f, 2);
    }
}
