package poker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testCompareTo() {
        Card.ComparatorByRank comparator = new Card.ComparatorByRank();
        assertEquals(0, comparator.compare(Card.of(Suit.Clubs, Rank.TWO), Card.of(Suit.Hearts, Rank.TWO)));
        assertEquals(-1, comparator.compare(Card.of(Suit.Clubs, Rank.TWO), Card.of(Suit.Clubs, Rank.THREE)));
        assertEquals(-2, comparator.compare(Card.of(Suit.Clubs, Rank.TWO), Card.of(Suit.Clubs, Rank.FOUR)));
        assertEquals(-12, comparator.compare(Card.of(Suit.Clubs, Rank.TWO), Card.of(Suit.Clubs, Rank.ACE)));

        assertEquals(0, comparator.compare(Card.of(Suit.Hearts, Rank.TWO), Card.of(Suit.Clubs, Rank.TWO)));
        assertEquals(1, comparator.compare(Card.of(Suit.Clubs, Rank.THREE), Card.of(Suit.Clubs, Rank.TWO)));
        assertEquals(2, comparator.compare(Card.of(Suit.Clubs, Rank.FOUR), Card.of(Suit.Clubs, Rank.TWO)));
        assertEquals(12, comparator.compare(Card.of(Suit.Clubs, Rank.ACE), Card.of(Suit.Clubs, Rank.TWO)));
    }
}
