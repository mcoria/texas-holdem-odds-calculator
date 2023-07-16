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
import static poker.juegos.Juego.Tipo.ESCALERA_COLOR;

public class EscaleraColorTest {


    @Test
    public void testCargarEscaleraColor_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.SIX),
                Card.of(Suit.CLUBS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.EIGHT),
                Card.of(Suit.CLUBS, Rank.NINE),
                Card.of(Suit.CLUBS, Rank.TEN),
                Card.of(Suit.CLUBS, Rank.ACE)
        ));

        Assert.assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.SIX),
                        Card.of(Suit.CLUBS, Rank.SEVEN),
                        Card.of(Suit.CLUBS, Rank.EIGHT),
                        Card.of(Suit.CLUBS, Rank.NINE),
                        Card.of(Suit.CLUBS, Rank.TEN)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscaleraColor_02() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.CLUBS, Rank.FOUR),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SIX),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.ACE)
        ));

        assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.TWO),
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.CLUBS, Rank.FOUR),
                        Card.of(Suit.CLUBS, Rank.FIVE),
                        Card.of(Suit.CLUBS, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCargarEscaleraColor_03() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.CLUBS, Rank.THREE),
                Card.of(Suit.CLUBS, Rank.FOUR),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.ACE)
        ));

        assertEquals(ESCALERA_COLOR, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.CLUBS, Rank.TWO),
                        Card.of(Suit.CLUBS, Rank.THREE),
                        Card.of(Suit.CLUBS, Rank.FOUR),
                        Card.of(Suit.CLUBS, Rank.FIVE),
                        Card.of(Suit.CLUBS, Rank.ACE)
                ), juego.getCards()
        );
    }

    @Test
    public void testCompararEscalera_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.HEARTS, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN),
                Card.of(Suit.HEARTS, Rank.KING),
                Card.of(Suit.HEARTS, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.SIX),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.EIGHT),
                Card.of(Suit.DIAMONDS, Rank.NINE),
                Card.of(Suit.DIAMONDS, Rank.TEN)
        ));

        Juego juego3 = Juego.cargarJuego(Set.of(
                Card.of(Suit.SPADES, Rank.TWO),
                Card.of(Suit.SPADES, Rank.THREE),
                Card.of(Suit.SPADES, Rank.FOUR),
                Card.of(Suit.SPADES, Rank.FIVE),
                Card.of(Suit.SPADES, Rank.ACE)
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
                Card.of(Suit.HEARTS, Rank.TEN),
                Card.of(Suit.HEARTS, Rank.JACK),
                Card.of(Suit.HEARTS, Rank.QUEEN),
                Card.of(Suit.HEARTS, Rank.KING),
                Card.of(Suit.HEARTS, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.TEN),
                Card.of(Suit.DIAMONDS, Rank.JACK),
                Card.of(Suit.DIAMONDS, Rank.QUEEN),
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.DIAMONDS, Rank.ACE)
        ));


        assertEquals(ESCALERA_COLOR, juego1.getType());
        assertEquals(ESCALERA_COLOR, juego2.getType());

        assertTrue(juego1.compareTo(juego2) == 0);
        assertTrue(juego2.compareTo(juego1) == 0);
    }

    @Test
    public void testCompararEscalera_03() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.DIAMONDS, Rank.FOUR),
                Card.of(Suit.DIAMONDS, Rank.FIVE),
                Card.of(Suit.DIAMONDS, Rank.ACE)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.HEARTS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FOUR),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SIX)
        ));


        assertEquals(ESCALERA_COLOR, juego1.getType());
        assertEquals(ESCALERA_COLOR, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }


}
