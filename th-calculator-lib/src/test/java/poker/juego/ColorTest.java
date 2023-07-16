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
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.NINE),
                Card.of(Suit.CLUBS, Rank.JACK),
                Card.of(Suit.CLUBS, Rank.ACE)
        ));

        Assert.assertEquals(COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.CLUBS, Rank.SEVEN),
                        Card.of(Suit.CLUBS, Rank.NINE),
                        Card.of(Suit.CLUBS, Rank.JACK),
                        Card.of(Suit.CLUBS, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararColor_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.NINE),
                Card.of(Suit.CLUBS, Rank.JACK),
                Card.of(Suit.CLUBS, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.HEARTS, Rank.THREE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.HEARTS, Rank.NINE),
                Card.of(Suit.HEARTS, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.ACE)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.HEARTS, Rank.THREE),
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.NINE),
                Card.of(Suit.HEARTS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Juego juego4 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.HEARTS, Rank.THREE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.HEARTS, Rank.NINE),
                Card.of(Suit.HEARTS, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.ACE)
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
