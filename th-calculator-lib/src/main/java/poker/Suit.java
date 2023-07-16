package poker;

public enum Suit {
    CLUBS('\u2663'), DIAMONDS('\u2666'), HEARTS('\u2764'), SPADES('\u2660');

    private final char symbol;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
