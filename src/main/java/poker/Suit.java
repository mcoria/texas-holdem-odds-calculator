package poker;

public enum Suit {
    Clubs('\u2663'), Diamonds('\u2666'), Hearts('\u2764'), Spades('\u2660');

    private final char symbol;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
