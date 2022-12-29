package poker.simulations;

import poker.Card;
import poker.Rank;

import java.util.Objects;

public class Dupla {
    private final Rank maxRank;
    private final Rank minRank;
    private final boolean isPair;

    private final boolean sameColor;

    public Dupla(Card card1, Card card2) {
        int rankCompare = card1.getRank().compareTo(card2.getRank());
        if(rankCompare > 0){
            this.maxRank = card1.getRank();
            this.minRank = card2.getRank();
            this.isPair = false;
        } else if (rankCompare < 0) {
            this.maxRank = card2.getRank();
            this.minRank = card1.getRank();
            this.isPair = false;
        } else {
            this.maxRank = card1.getRank();
            this.minRank = card2.getRank();
            this.isPair = true;
        }

        this.sameColor = card1.getSuit().equals(card2.getSuit());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Dupla){
            Dupla other = (Dupla) obj;
            return this.maxRank.equals(other.maxRank) && this.minRank.equals(other.minRank);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxRank, minRank, isPair, sameColor);
    }

    @Override
    public String toString() {
        return String.format("{%s %s}%s", this.maxRank.toString(), this.minRank.toString(), sameColor ? "*" : " ");
    }
}
