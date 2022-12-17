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
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.KING)
        ));

        Assert.assertEquals(FULL, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Diamonds, Rank.THREE),
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Clubs, Rank.FIVE),
                        Card.of(Suit.Diamonds, Rank.FIVE)
                ), juego.getCards()
        );
    }

    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.TWO),
                Card.of(Suit.Spades, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Hearts, Rank.SEVEN)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.TWO),
                Card.of(Suit.Spades, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Hearts, Rank.SEVEN)
        ));


        assertEquals(FULL, juego1.getType());
        assertEquals(FULL, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }

    @Test
    public void testComparar_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.THREE),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Spades, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Hearts, Rank.SEVEN)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.TWO),
                Card.of(Suit.Spades, Rank.EIGHT),
                Card.of(Suit.Diamonds, Rank.EIGHT),
                Card.of(Suit.Hearts, Rank.EIGHT)
        ));


        assertEquals(FULL, juego1.getType());
        assertEquals(FULL, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }
}
