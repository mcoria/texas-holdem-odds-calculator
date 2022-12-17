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
import static poker.juegos.Juego.Tipo.PAR_DOBLE;

public class ParDobleTest {

    @Test
    public void testCargarParDoble_01() {
        Juego juego = Juego.cargarJuego(Set.of(
                Card.of(Suit.Clubs, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.THREE),
                Card.of(Suit.Hearts, Rank.FIVE),
                Card.of(Suit.Clubs, Rank.FIVE),
                Card.of(Suit.Hearts, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.KING)
        ));

        Assert.assertEquals(PAR_DOBLE, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.Hearts, Rank.FIVE),
                        Card.of(Suit.Clubs, Rank.FIVE),
                        Card.of(Suit.Hearts, Rank.SEVEN),
                        Card.of(Suit.Diamonds, Rank.SEVEN),
                        Card.of(Suit.Clubs, Rank.KING)
                ), juego.getCards()
        );
    }

    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Hearts, Rank.TWO),
                Card.of(Suit.Diamonds, Rank.TWO),
                Card.of(Suit.Spades, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.KING)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.Spades, Rank.SEVEN),
                Card.of(Suit.Diamonds, Rank.SEVEN),
                Card.of(Suit.Clubs, Rank.QUEEN),
                Card.of(Suit.Diamonds, Rank.KING),
                Card.of(Suit.Spades, Rank.KING)
        ));


        assertEquals(PAR_DOBLE, juego1.getType());
        assertEquals(PAR_DOBLE, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }
}
