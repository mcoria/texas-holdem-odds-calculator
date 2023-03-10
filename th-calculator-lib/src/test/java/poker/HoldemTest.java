package poker;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static poker.Suit.*;
import static poker.Rank.*;

public class HoldemTest {

    @Test
    public void testCalcularGanadores_01() {
        CommunityCards communityCards = new CommunityCards();
        communityCards.setFlop(Card.of(Clubs, TWO), Card.of(Clubs, THREE), Card.of(Hearts, FIVE));
        communityCards.setTurn(Card.of(Hearts, SEVEN));
        communityCards.setRiver(Card.of(Spades, NINE));

        Player player1 = new Player();
        player1.setPocketCards(
                Card.of(Hearts, ACE),
                Card.of(Spades, ACE)
        );

        Player player2 = new Player();
        player2.setPocketCards(
                Card.of(Hearts, KING),
                Card.of(Spades, KING)
        );

        Set<Player> ganadores = Holdem.calcularGanadores(List.of(player1, player2), communityCards);

        assertEquals(1, ganadores.size());
        assertTrue(ganadores.contains(player1));
    }

    @Test
    public void testCalcularGanadores_02() {
        CommunityCards communityCards = new CommunityCards();
        communityCards.setFlop(Card.of(Clubs, TWO), Card.of(Clubs, THREE), Card.of(Hearts, FIVE));
        communityCards.setTurn(Card.of(Hearts, SEVEN));
        communityCards.setRiver(Card.of(Spades, NINE));

        Player player1 = new Player();
        player1.setPocketCards(
                Card.of(Hearts, ACE),
                Card.of(Spades, ACE)
        );

        Player player2 = new Player();
        player2.setPocketCards(
                Card.of(Diamonds, ACE),
                Card.of(Spades, ACE)
        );

        Set<Player> ganadores = Holdem.calcularGanadores(List.of(player1, player2), communityCards);

        assertEquals(2, ganadores.size());
        assertTrue(ganadores.contains(player1));
        assertTrue(ganadores.contains(player2));
    }
}
