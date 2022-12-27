package poker.simulations;

import poker.Card;
import poker.Rank;

import java.util.Set;

public enum TipoCombinacion {
    PAR_As("par de As"),
    PAR_K("par de Ks"),
    PAR_Q("par de Qs"),
    PAR_J("par de Js"),
    PAR_10("par de 10s"),
    PAR_9("par de 9s"),
    PAR_8("par de 8s"),
    PAR_7("par de 7s"),
    PAR_6("par de 6s"),
    PAR_5("par de 5s"),
    PAR_4("par de 4s"),
    PAR_3("par de 3s"),
    PAR_2("par de 2s"),

    PROSPECT_COLOR("prospect color"),

    A_K("A y K"),

    OTRA("otra");

    private final String description;

    TipoCombinacion(String description) {
        this.description = description;
    }

    public static TipoCombinacion loadTipo(Set<Card> cards) {
        // Detectar par
        if (cards.stream().map(Card::getRank).distinct().count() == 1l) {
            Card theCard = cards.stream().findAny().get();
            switch (theCard.getRank()) {
                case ACE:
                    return PAR_As;
                case KING:
                    return PAR_K;
                case QUEEN:
                    return PAR_Q;
                case JACK:
                    return PAR_J;
                case TEN:
                    return PAR_10;
                case NINE:
                    return PAR_9;
                case EIGHT:
                    return PAR_8;
                case SEVEN:
                    return PAR_7;
                case SIX:
                    return PAR_6;
                case FIVE:
                    return PAR_5;
                case FOUR:
                    return PAR_4;
                case THREE:
                    return PAR_3;
                case TWO:
                    return PAR_2;
            }
        } else if (cards.stream().map(Card::getSuit).distinct().count() == 1l) {
            return PROSPECT_COLOR;
        } else {
            if (cards.stream().map(Card::getRank).filter(rank -> Rank.ACE.equals(rank) || Rank.KING.equals(rank)).count() == 2L) {
                return A_K;
            }
        }
        return OTRA;
    }

    public String getDescription() {
        return description;
    }
}
