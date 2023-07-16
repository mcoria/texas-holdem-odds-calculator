package poker.juego;

import org.junit.Assert;
import org.junit.Test;
import poker.Card;

import static org.junit.Assert.assertTrue;
import static poker.juegos.Juego.Tipo.*;

import poker.Suit;
import poker.Rank;
import poker.juegos.Juego;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PiernaTest {
    @Test
    public void testCargar_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        Assert.assertEquals(PIERNA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.CLUBS, Rank.FIVE),
                        Card.of(Suit.DIAMONDS, Rank.FIVE),
                        Card.of(Suit.DIAMONDS, Rank.SEVEN),
                        Card.of(Suit.CLUBS, Rank.KING)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargar_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN)
        ));

        Assert.assertEquals(PIERNA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.DIAMONDS, Rank.FOUR),
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.HEARTS, Rank.SEVEN),
                        Card.of(Suit.CLUBS, Rank.SEVEN),
                        Card.of(Suit.DIAMONDS, Rank.SEVEN)
                ), juego.getCards()
        );
    }

    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN)
        ));

        assertEquals(PIERNA, juego1.getType());
        assertEquals(PIERNA, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }

    @Test
    public void testComparar_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        assertEquals(PIERNA, juego1.getType());
        assertEquals(PIERNA, juego2.getType());

        assertTrue(juego1.compareTo(juego2) > 0);
        assertTrue(juego2.compareTo(juego1) < 0);
    }
}
