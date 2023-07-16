package poker.juego;

import org.junit.Test;
import poker.Card;
import poker.Rank;
import poker.Suit;
import poker.juegos.Juego;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static poker.juegos.Juego.Tipo.POKER;

public class PokerTest {

    // Tipo={POKER}; Cards=[♠8, ♦8, ♣8, ❤8, ♠10]
    // Tipo={POKER}; Cards=[♠8, ♦8, ♣8, ❤8, ♦A]
    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.EIGHT),
                Card.of(Suit.DIAMONDS, Rank.EIGHT),
                Card.of(Suit.SPADES, Rank.EIGHT),
                Card.of(Suit.CLUBS, Rank.EIGHT),
                Card.of(Suit.SPADES, Rank.TEN)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.EIGHT),
                Card.of(Suit.DIAMONDS, Rank.EIGHT),
                Card.of(Suit.SPADES, Rank.EIGHT),
                Card.of(Suit.CLUBS, Rank.EIGHT),
                Card.of(Suit.DIAMONDS, Rank.ACE)
        ));


        assertEquals(POKER, juego1.getType());
        assertEquals(POKER, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }
}
