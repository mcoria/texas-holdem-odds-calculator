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
import static poker.juegos.Juego.Tipo.ESCALERA;

public class EscaleraTest {


    @Test
    public void testCargarEscalera_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FOUR),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.SIX),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Spades, Rank.KING)
        ));

        Assert.assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.TWO),
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Hearts, Rank.FOUR),
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Diamonds, Rank.SIX)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FOUR),
                Card.of(Suit.Hearts, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Diamonds, Rank.QUEEN),
                Card.of(Suit.Spades, Rank.KING),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Hearts, Rank.TEN),
                        Card.of(Suit.Diamonds, Rank.JACK),
                        Card.of(Suit.Diamonds, Rank.QUEEN),
                        Card.of(Suit.Spades, Rank.KING),
                        Card.of(Suit.Spades, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_03() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FOUR),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.TWO),
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Hearts, Rank.FOUR),
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Spades, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_04() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FOUR),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.SIX),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.EIGHT)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Hearts, Rank.FOUR),
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Diamonds, Rank.SIX),
                        Card.of(Suit.Diamonds, Rank.SEVEN),
                        Card.of(Suit.Diamonds, Rank.EIGHT)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararEscalera_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Diamonds, Rank.QUEEN),
                Card.of(Suit.Spades, Rank.KING),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.SIX),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.EIGHT),
                Card.of(Suit.Spades, Rank.NINE),
                Card.of(Suit.Spades, Rank.TEN)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Diamonds, Rank.FOUR),
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        assertEquals(ESCALERA, juego1.getType());
        assertEquals(ESCALERA, juego2.getType());
        assertEquals(ESCALERA, juego3.getType());

        assertTrue(juego1.compareTo(juego2) > 0);
        assertTrue(juego1.compareTo(juego3) > 0);

        assertTrue(juego2.compareTo(juego1) < 0);
        assertTrue(juego2.compareTo(juego3) > 0);

        assertTrue(juego3.compareTo(juego1) < 0);
        assertTrue(juego3.compareTo(juego2) < 0);
    }

    @Test
    public void testCompararEscalera_02() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Diamonds, Rank.QUEEN),
                Card.of(Suit.Spades, Rank.KING),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Diamonds, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Diamonds, Rank.QUEEN),
                Card.of(Suit.Spades, Rank.KING),
                Card.of(Suit.Spades, Rank.ACE)
        ));


        assertEquals(ESCALERA, juego1.getType());
        assertEquals(ESCALERA, juego2.getType());

        assertTrue(juego1.compareTo(juego2) == 0);
        assertTrue(juego2.compareTo(juego1) == 0);
    }

    //Tipo={ESCALERA}; Cards=[♦2, ♠3, ♦4, ♣5, ❤A]
    //Tipo={ESCALERA}; Cards=[♦2, ♠3, ♦4, ♣5, ♠6]
    @Test
    public void testCompararEscalera_03() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Diamonds, Rank.FOUR),
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Diamonds, Rank.FOUR),
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Spades, Rank.SIX)
        ));


        assertEquals(ESCALERA, juego1.getType());
        assertEquals(ESCALERA, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }

}
