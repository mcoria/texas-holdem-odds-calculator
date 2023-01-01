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
import static poker.juegos.Juego.Tipo.NADA;

public class NadaTest {

    @Test
    public void  testCargarNada_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.NINE),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Assert.assertEquals(NADA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Hearts, Rank.SEVEN),
                        Card.of(Suit.Diamonds, Rank.NINE),
                        Card.of(Suit.Diamonds, Rank.JACK),
                        Card.of(Suit.Spades, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompareToNada_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Spades, Rank.NINE),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Spades, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.NINE),
                Card.of(Suit.Spades, Rank.JACK),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        assertEquals(NADA, juego1.getType());

        assertEquals(NADA, juego2.getType());

        assertTrue( juego1.compareTo(juego2) == 0);

        assertTrue( juego2.compareTo(juego1) == 0);

    }

    @Test
    public void testCompareToNada_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.NINE),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.NINE),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Spades, Rank.KING)     // Difieren en este elemento
        ));

        assertEquals(NADA, juego1.getType());

        assertEquals(NADA, juego2.getType());

        assertTrue( juego1.compareTo(juego2) > 0);

        assertTrue( juego2.compareTo(juego1) < 0);

    }

}
