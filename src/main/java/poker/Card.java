package poker;

import java.util.Comparator;

import static poker.Rank.*;
import static poker.Suit.*;

public enum Card {
    CLUBS_TWO(Clubs, TWO),
    CLUBS_THREE(Clubs, THREE),
    CLUBS_FOUR(Clubs, FOUR),
    CLUBS_FIVE(Clubs, FIVE),
    CLUBS_SIX(Clubs, SIX),
    CLUBS_SEVEN(Clubs, SEVEN),
    CLUBS_EIGHT(Clubs, EIGHT),
    CLUBS_NINE(Clubs, NINE),
    CLUBS_TEN(Clubs, TEN),
    CLUBS_JACK(Clubs, JACK),
    CLUBS_QUEEN(Clubs, QUEEN),
    CLUBS_KING(Clubs, KING),
    CLUBS_ACE(Clubs, ACE),

    DIAMONDS_TWO(Diamonds, TWO),
    DIAMONDS_THREE(Diamonds, THREE),
    DIAMONDS_FOUR(Diamonds, FOUR),
    DIAMONDS_FIVE(Diamonds, FIVE),
    DIAMONDS_SIX(Diamonds, SIX),
    DIAMONDS_SEVEN(Diamonds, SEVEN),
    DIAMONDS_EIGHT(Diamonds, EIGHT),
    DIAMONDS_NINE(Diamonds, NINE),
    DIAMONDS_TEN(Diamonds, TEN),
    DIAMONDS_JACK(Diamonds, JACK),
    DIAMONDS_QUEEN(Diamonds, QUEEN),
    DIAMONDS_KING(Diamonds, KING),
    DIAMONDS_ACE(Diamonds, ACE),

    HEARTS_TWO(Hearts, TWO),
    HEARTS_THREE(Hearts, THREE),
    HEARTS_FOUR(Hearts, FOUR),
    HEARTS_FIVE(Hearts, FIVE),
    HEARTS_SIX(Hearts, SIX),
    HEARTS_SEVEN(Hearts, SEVEN),
    HEARTS_EIGHT(Hearts, EIGHT),
    HEARTS_NINE(Hearts, NINE),
    HEARTS_TEN(Hearts, TEN),
    HEARTS_JACK(Hearts, JACK),
    HEARTS_QUEEN(Hearts, QUEEN),
    HEARTS_KING(Hearts, KING),
    HEARTS_ACE(Hearts, ACE),

    SPADES_TWO(Spades, TWO),
    SPADES_THREE(Spades, THREE),
    SPADES_FOUR(Spades, FOUR),
    SPADES_FIVE(Spades, FIVE),
    SPADES_SIX(Spades, SIX),
    SPADES_SEVEN(Spades, SEVEN),
    SPADES_EIGHT(Spades, EIGHT),
    SPADES_NINE(Spades, NINE),
    SPADES_TEN(Spades, TEN),
    SPADES_JACK(Spades, JACK),
    SPADES_QUEEN(Spades, QUEEN),
    SPADES_KING(Spades, KING),
    SPADES_ACE(Spades, ACE);

    private final Suit suit;
    private final Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return suit.toString() + rank.toString();
    }

    public static Card of(Suit suit, Rank rank) {
        for (Card theCard : Card.values()) {
            if (theCard.suit.equals(suit) && theCard.rank.equals(rank)) {
                return theCard;
            }
        }
        return null;
    }

    public static Card of(Rank rank, Suit suit) {
        for (Card theCard : Card.values()) {
            if (theCard.suit.equals(suit) && theCard.rank.equals(rank)) {
                return theCard;
            }
        }
        return null;
    }

    public static class ComparatorByRank implements Comparator<Card> {
        @Override
        public int compare(Card o1, Card o2) {
            return o1.rank.compareTo(o2.rank);
        }
    }
}
