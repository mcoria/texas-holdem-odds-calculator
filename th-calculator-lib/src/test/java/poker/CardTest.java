package poker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testCompareTo() {
        Card.ComparatorByRank comparator = new Card.ComparatorByRank();
        assertEquals(0, comparator.compare(Card.of(Suit.CLUBS, Rank.TWO), Card.of(Suit.HEARTS, Rank.TWO)));
        assertEquals(-1, comparator.compare(Card.of(Suit.CLUBS, Rank.TWO), Card.of(Suit.CLUBS, Rank.THREE)));
        assertEquals(-2, comparator.compare(Card.of(Suit.CLUBS, Rank.TWO), Card.of(Suit.CLUBS, Rank.FOUR)));
        assertEquals(-12, comparator.compare(Card.of(Suit.CLUBS, Rank.TWO), Card.of(Suit.CLUBS, Rank.ACE)));

        assertEquals(0, comparator.compare(Card.of(Suit.HEARTS, Rank.TWO), Card.of(Suit.CLUBS, Rank.TWO)));
        assertEquals(1, comparator.compare(Card.of(Suit.CLUBS, Rank.THREE), Card.of(Suit.CLUBS, Rank.TWO)));
        assertEquals(2, comparator.compare(Card.of(Suit.CLUBS, Rank.FOUR), Card.of(Suit.CLUBS, Rank.TWO)));
        assertEquals(12, comparator.compare(Card.of(Suit.CLUBS, Rank.ACE), Card.of(Suit.CLUBS, Rank.TWO)));
    }
}
