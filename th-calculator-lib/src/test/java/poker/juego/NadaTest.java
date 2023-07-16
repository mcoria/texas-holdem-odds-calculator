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
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Assert.assertEquals(NADA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.HEARTS, Rank.SEVEN),
                        Card.of(Suit.DIAMONDS, Rank.NINE),
                        Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.SPADES, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompareToNada_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.SPADES, Rank.NINE),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.SPADES, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.SPADES, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.ACE)
        ));

        assertEquals(NADA, juego1.getType());

        assertEquals(NADA, juego2.getType());

        assertTrue( juego1.compareTo(juego2) == 0);

        assertTrue( juego2.compareTo(juego1) == 0);

    }

    @Test
    public void testCompareToNada_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.KING)     // Difieren en este elemento
        ));

        assertEquals(NADA, juego1.getType());

        assertEquals(NADA, juego2.getType());

        assertTrue( juego1.compareTo(juego2) > 0);

        assertTrue( juego2.compareTo(juego1) < 0);

    }

}
