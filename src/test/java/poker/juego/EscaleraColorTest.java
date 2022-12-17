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
import static poker.juegos.Juego.Tipo.ESCALERA_COLOR;

public class EscaleraColorTest {


    @Test
    public void testCargarEscaleraColor_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.SIX),
                Card.of(Suit.Clubs, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.EIGHT),
                Card.of(Suit.Clubs, Rank.NINE),
                Card.of(Suit.Clubs, Rank.TEN),
                Card.of(Suit.Clubs, Rank.ACE)
        ));

        Assert.assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.SIX),
                        Card.of(Suit.Clubs, Rank.SEVEN),
                        Card.of(Suit.Clubs, Rank.EIGHT),
                        Card.of(Suit.Clubs, Rank.NINE),
                        Card.of(Suit.Clubs, Rank.TEN)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscaleraColor_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Clubs, Rank.FOUR),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SIX),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.ACE)
        ));

        assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.TWO),
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Clubs, Rank.FOUR),
                        Card.of(Suit.Clubs, Rank.FIVE),
                        Card.of(Suit.Clubs, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscaleraColor_03() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Clubs, Rank.THREE),
                Card.of(Suit.Clubs, Rank.FOUR),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Diamonds, Rank.FIVE),
                Card.of(Suit.Clubs, Rank.ACE)
        ));

        assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Clubs, Rank.TWO),
                        Card.of(Suit.Clubs, Rank.THREE),
                        Card.of(Suit.Clubs, Rank.FOUR),
                        Card.of(Suit.Clubs, Rank.FIVE),
                        Card.of(Suit.Clubs, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararEscalera_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TEN),
                Card.of(Suit.Hearts, Rank.JACK),
                Card.of(Suit.Hearts, Rank.QUEEN),
                Card.of(Suit.Hearts, Rank.KING),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Diamonds, Rank.SIX),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.EIGHT),
                Card.of(Suit.Diamonds, Rank.NINE),
                Card.of(Suit.Diamonds, Rank.TEN)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Spades, Rank.TWO),
                Card.of(Suit.Spades, Rank.THREE),
                Card.of(Suit.Spades, Rank.FOUR),
                Card.of(Suit.Spades, Rank.FIVE),
                Card.of(Suit.Spades, Rank.ACE)
        ));

        assertEquals(ESCALERA_COLOR, juego1.getType());
        assertEquals(ESCALERA_COLOR, juego2.getType());
        assertEquals(ESCALERA_COLOR, juego3.getType());

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
                Card.of(Suit.Hearts, Rank.JACK),
                Card.of(Suit.Hearts, Rank.QUEEN),
                Card.of(Suit.Hearts, Rank.KING),
                Card.of(Suit.Hearts, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Diamonds, Rank.TEN),
                Card.of(Suit.Diamonds, Rank.JACK),
                Card.of(Suit.Diamonds, Rank.QUEEN),
                Card.of(Suit.Diamonds, Rank.KING),
                Card.of(Suit.Diamonds, Rank.ACE)
        ));


        assertEquals(ESCALERA_COLOR, juego1.getType());
        assertEquals(ESCALERA_COLOR, juego2.getType());

        assertTrue(juego1.compareTo(juego2) == 0);
        assertTrue(juego2.compareTo(juego1) == 0);
    }


}
