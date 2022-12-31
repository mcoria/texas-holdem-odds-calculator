package poker.simulations;

import poker.Card;
import poker.Rank;

import java.util.Objects;

public class PocketCardsGrouping {
    private final Rank maxRank;
    private final Rank minRank;
    private final boolean isPair;
    private final boolean sameColor;
    private final boolean posibleEscalera;

    public PocketCardsGrouping(Card card1, Card card2) {
        int rankCompare = card1.getRank().compareTo(card2.getRank());
        if (rankCompare > 0) {
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

        if (rankCompare != 0) {
            if (Math.abs(rankCompare) < 5) {
                this.posibleEscalera = true;
            } else if (this.maxRank.equals(Rank.ACE) && (this.minRank.equals(Rank.TWO) || this.minRank.equals(Rank.THREE) || this.minRank.equals(Rank.FOUR) || this.minRank.equals(Rank.FIVE))) {
                this.posibleEscalera = true;
            } else {
                this.posibleEscalera = false;
            }
        } else {
            this.posibleEscalera = false;
        }
        this.sameColor = card1.getSuit().equals(card2.getSuit());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PocketCardsGrouping) {
            PocketCardsGrouping other = (PocketCardsGrouping) obj;
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
        return String.format("{%s %s}%s%s", this.maxRank.toString(), this.minRank.toString(), sameColor ? "*" : " ", posibleEscalera ? "+" : " ");
    }

    public Rank getMaxRank() {
        return maxRank;
    }

    public Rank getMinRank() {
        return minRank;
    }

    public boolean isPair() {
        return isPair;
    }

    public boolean isPosibleEscalera() {
        return posibleEscalera;
    }
}
