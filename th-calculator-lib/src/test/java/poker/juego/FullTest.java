package poker.juego;

import org.junit.Assert;
import org.junit.Test;
import poker.Card;
import poker.Suit;
import poker.Rank;
import poker.juegos.Juego;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static poker.juegos.Juego.Tipo.FULL;

public class FullTest {
    @Test
    public void testFull_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        Assert.assertEquals(FULL, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.DIAMONDS, Rank.THREE),
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.CLUBS, Rank.FIVE),
                        Card.of(Suit.DIAMONDS, Rank.FIVE)
                ), juego.getCards()
        );
    }

    @Test
    public void testFull_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.TEN),
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.CLUBS, Rank.THREE)
        ));

        assertEquals(FULL, juego.getType());

        assertTrue(juego.getCards().containsAll(Set.of(Card.of(Suit.CLUBS, Rank.TEN), Card.of(Suit.DIAMONDS, Rank.TEN), Card.of(Suit.HEARTS, Rank.TEN))));
    }

    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.SPADES, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.HEARTS, Rank.SEVEN)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.SPADES, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.HEARTS, Rank.SEVEN)
        ));


        assertEquals(FULL, juego1.getType());
        assertEquals(FULL, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }

    @Test
    public void testComparar_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.SPADES, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.HEARTS, Rank.SEVEN)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.SPADES, Rank.EIGHT),
                Card.of(Suit.DIAMONDS, Rank.EIGHT),
                Card.of(Suit.HEARTS, Rank.EIGHT)
        ));


        assertEquals(FULL, juego1.getType());
        assertEquals(FULL, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }
}
