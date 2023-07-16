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
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SIX),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.KING)
        ));

        Assert.assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.TWO),
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.HEARTS, Rank.FOUR),
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.DIAMONDS, Rank.SIX)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.DIAMONDS, Rank.QUEEN),
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.HEARTS, Rank.TEN),
                        Card.of(Suit.DIAMONDS, Rank.JACK),
                        Card.of(Suit.DIAMONDS, Rank.QUEEN),
                        Card.of(Suit.SPADES, Rank.KING),
                        Card.of(Suit.SPADES, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_03() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.TWO),
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.HEARTS, Rank.FOUR),
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.SPADES, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscalera_04() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.SIX),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.EIGHT)
        ));

        assertEquals(ESCALERA, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.HEARTS, Rank.FOUR),
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.DIAMONDS, Rank.SIX),
                        Card.of(Suit.DIAMONDS, Rank.SEVEN),
                        Card.of(Suit.DIAMONDS, Rank.EIGHT)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararEscalera_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.DIAMONDS, Rank.QUEEN),
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.EIGHT),
                Card.of(Suit.SPADES, Rank.NINE),
                Card.of(Suit.SPADES, Rank.TEN)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.SPADES, Rank.ACE)
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
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.DIAMONDS, Rank.QUEEN),
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.DIAMONDS, Rank.QUEEN),
                Card.of(Suit.SPADES, Rank.KING),
                Card.of(Suit.SPADES, Rank.ACE)
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
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.SPADES, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.SPADES, Rank.SIX)
        ));


        assertEquals(ESCALERA, juego1.getType());
        assertEquals(ESCALERA, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }

}
