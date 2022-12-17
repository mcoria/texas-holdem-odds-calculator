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
import static poker.juegos.Juego.Tipo.COLOR;
import static poker.juegos.Juego.Tipo.NADA;

public class ColorTest {


    @Test
    public void testCargarColor_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Clubs, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.NINE),
                Card.of(Suit.Clubs, Rank.JACK),
                Card.of(Suit.Clubs, Rank.ACE)
        ));

        Assert.assertEquals(COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Clubs, Rank.SEVEN),
                        Card.of(Suit.Clubs, Rank.NINE),
                        Card.of(Suit.Clubs, Rank.JACK),
                        Card.of(Suit.Clubs, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararColor_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Clubs, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.NINE),
                Card.of(Suit.Clubs, Rank.JACK),
                Card.of(Suit.Clubs, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Hearts, Rank.THREE),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Hearts, Rank.NINE),
                Card.of(Suit.Hearts, Rank.JACK),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Hearts, Rank.THREE),
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.NINE),
                Card.of(Suit.Hearts, Rank.JACK),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Juego juego4 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Hearts, Rank.THREE),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SIX),
                Card.of(Suit.Hearts, Rank.NINE),
                Card.of(Suit.Hearts, Rank.JACK),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        assertEquals(COLOR, juego1.getType());
        assertEquals(COLOR, juego2.getType());
        assertEquals(NADA, juego3.getType());
        assertEquals(COLOR, juego4.getType());

        assertTrue(juego1.compareTo(juego2) == 0 );
        assertTrue(juego2.compareTo(juego1) == 0 );
        assertTrue(juego1.compareTo(juego4) > 0 );
        assertTrue(juego4.compareTo(juego1) < 0 );

        assertTrue(juego1.compareTo(juego3) > 0);
        assertTrue(juego3.compareTo(juego1) < 0);


    }
}
