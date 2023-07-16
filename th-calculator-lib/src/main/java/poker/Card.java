package poker;

import java.util.Comparator;

import static poker.Rank.*;
import static poker.Suit.*;

public enum Card {
    CLUBS_TWO(CLUBS, TWO),
    CLUBS_THREE(CLUBS, THREE),
    CLUBS_FOUR(CLUBS, FOUR),
    CLUBS_FIVE(CLUBS, FIVE),
    CLUBS_SIX(CLUBS, SIX),
    CLUBS_SEVEN(CLUBS, SEVEN),
    CLUBS_EIGHT(CLUBS, EIGHT),
    CLUBS_NINE(CLUBS, NINE),
    CLUBS_TEN(CLUBS, TEN),
    CLUBS_JACK(CLUBS, JACK),
    CLUBS_QUEEN(CLUBS, QUEEN),
    CLUBS_KING(CLUBS, KING),
    CLUBS_ACE(CLUBS, ACE),

    DIAMONDS_TWO(DIAMONDS, TWO),
    DIAMONDS_THREE(DIAMONDS, THREE),
    DIAMONDS_FOUR(DIAMONDS, FOUR),
    DIAMONDS_FIVE(DIAMONDS, FIVE),
    DIAMONDS_SIX(DIAMONDS, SIX),
    DIAMONDS_SEVEN(DIAMONDS, SEVEN),
    DIAMONDS_EIGHT(DIAMONDS, EIGHT),
    DIAMONDS_NINE(DIAMONDS, NINE),
    DIAMONDS_TEN(DIAMONDS, TEN),
    DIAMONDS_JACK(DIAMONDS, JACK),
    DIAMONDS_QUEEN(DIAMONDS, QUEEN),
    DIAMONDS_KING(DIAMONDS, KING),
    DIAMONDS_ACE(DIAMONDS, ACE),

    HEARTS_TWO(HEARTS, TWO),
    HEARTS_THREE(HEARTS, THREE),
    HEARTS_FOUR(HEARTS, FOUR),
    HEARTS_FIVE(HEARTS, FIVE),
    HEARTS_SIX(HEARTS, SIX),
    HEARTS_SEVEN(HEARTS, SEVEN),
    HEARTS_EIGHT(HEARTS, EIGHT),
    HEARTS_NINE(HEARTS, NINE),
    HEARTS_TEN(HEARTS, TEN),
    HEARTS_JACK(HEARTS, JACK),
    HEARTS_QUEEN(HEARTS, QUEEN),
    HEARTS_KING(HEARTS, KING),
    HEARTS_ACE(HEARTS, ACE),

    SPADES_TWO(SPADES, TWO),
    SPADES_THREE(SPADES, THREE),
    SPADES_FOUR(SPADES, FOUR),
    SPADES_FIVE(SPADES, FIVE),
    SPADES_SIX(SPADES, SIX),
    SPADES_SEVEN(SPADES, SEVEN),
    SPADES_EIGHT(SPADES, EIGHT),
    SPADES_NINE(SPADES, NINE),
    SPADES_TEN(SPADES, TEN),
    SPADES_JACK(SPADES, JACK),
    SPADES_QUEEN(SPADES, QUEEN),
    SPADES_KING(SPADES, KING),
    SPADES_ACE(SPADES, ACE);

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
