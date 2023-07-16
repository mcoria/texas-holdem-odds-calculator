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
                Card.of(Suit.CLUBS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.THREE),
                Card.of(Suit.HEARTS, Rank.FIVE),
                Card.of(Suit.CLUBS, Rank.FIVE),
                Card.of(Suit.HEARTS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.KING)
        ));

        Assert.assertEquals(PAR_DOBLE, juego.getType());

        assertEquals(
                Set.of(
                        Card.of(Suit.HEARTS, Rank.FIVE),
                        Card.of(Suit.CLUBS, Rank.FIVE),
                        Card.of(Suit.HEARTS, Rank.SEVEN),
                        Card.of(Suit.DIAMONDS, Rank.SEVEN),
                        Card.of(Suit.CLUBS, Rank.KING)
                ), juego.getCards()
        );
    }

    @Test
    public void testComparar_01() {
        Juego juego1 = Juego.cargarJuego(Set.of(
                Card.of(Suit.HEARTS, Rank.TWO),
                Card.of(Suit.DIAMONDS, Rank.TWO),
                Card.of(Suit.SPADES, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.KING)
        ));

        Juego juego2 = Juego.cargarJuego(Set.of(
                Card.of(Suit.SPADES, Rank.SEVEN),
                Card.of(Suit.DIAMONDS, Rank.SEVEN),
                Card.of(Suit.CLUBS, Rank.QUEEN),
                Card.of(Suit.DIAMONDS, Rank.KING),
                Card.of(Suit.SPADES, Rank.KING)
        ));


        assertEquals(PAR_DOBLE, juego1.getType());
        assertEquals(PAR_DOBLE, juego2.getType());

        assertTrue(juego1.compareTo(juego2) < 0);
        assertTrue(juego2.compareTo(juego1) > 0);
    }
}
