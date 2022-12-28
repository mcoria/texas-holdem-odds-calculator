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
    A_KQJ("A y K/Q/J"),
    A_10_9_8("A y 10/9/8"),
    A_7_6_5("A y 7/6/5"),
    A_4_3_2("A y 4/3/2"),

    K_QJ10("K y Q/J/10"),
    K_9_8_7("K y 9/8/7"),
    K_6_5_4("K y 6/5/4"),
    K_3_2("K y 3/2"),
    Q_J_10_9("Q y J/10/9"),
    Q_8_7_6("Q y 8/7/6"),
    Q_5_4_3_2("Q y 5/4/3/2"),

    J_10_9_8("J y 10/9/8"),

    J_7_6_5("J y 7/6/5"),

    J_4_3_2("J y 4/3/2"),

    DOS_DE_10_9_8_7_6("dos de 10/9/8/7/6"),

    DOS_DE_9_8_7_6_5("dos de 9/8/7/6/5"),

    PROSPECT_COLOR("prospect color"),
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
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.ACE.equals(rank)).findAny().isPresent()) {
            if (cards.stream().map(Card::getRank).filter(rank -> Rank.KING.equals(rank) || Rank.QUEEN.equals(rank) || Rank.JACK.equals(rank)).findAny().isPresent()) {
                return A_KQJ;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.TEN.equals(rank) || Rank.NINE.equals(rank) || Rank.EIGHT.equals(rank)).findAny().isPresent()) {
                return A_10_9_8;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.SEVEN.equals(rank) || Rank.SIX.equals(rank) || Rank.FIVE.equals(rank)).findAny().isPresent()) {
                return A_7_6_5;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.FOUR.equals(rank) || Rank.THREE.equals(rank) || Rank.TWO.equals(rank)).findAny().isPresent()) {
                return A_4_3_2;
            }
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.KING.equals(rank)).findAny().isPresent()) {
            if (cards.stream().map(Card::getRank).filter(rank -> Rank.QUEEN.equals(rank) || Rank.JACK.equals(rank) || Rank.TEN.equals(rank)).findAny().isPresent()) {
                return K_QJ10;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.NINE.equals(rank) || Rank.EIGHT.equals(rank) || Rank.SEVEN.equals(rank)).findAny().isPresent()) {
                return K_9_8_7;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.SIX.equals(rank) || Rank.FIVE.equals(rank) || Rank.FOUR.equals(rank)).findAny().isPresent()) {
                return K_6_5_4;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.THREE.equals(rank) || Rank.TWO.equals(rank)).findAny().isPresent()) {
                return K_3_2;
            }
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.QUEEN.equals(rank)).findAny().isPresent()) {
            if (cards.stream().map(Card::getRank).filter(rank -> Rank.JACK.equals(rank) || Rank.TEN.equals(rank) || Rank.NINE.equals(rank)).findAny().isPresent()) {
                return Q_J_10_9;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.EIGHT.equals(rank) || Rank.SEVEN.equals(rank) || Rank.SIX.equals(rank)).findAny().isPresent()) {
                return Q_8_7_6;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.FIVE.equals(rank) || Rank.FOUR.equals(rank) || Rank.THREE.equals(rank) || Rank.TWO.equals(rank)).findAny().isPresent()) {
                return Q_5_4_3_2;
            }
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.JACK.equals(rank)).findAny().isPresent()) {
            if (cards.stream().map(Card::getRank).filter(rank -> Rank.TEN.equals(rank) || Rank.NINE.equals(rank) || Rank.EIGHT.equals(rank)).findAny().isPresent()) {
                return J_10_9_8;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.SEVEN.equals(rank) || Rank.SIX.equals(rank) || Rank.FIVE.equals(rank)).findAny().isPresent()) {
                return J_7_6_5;
            } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.FOUR.equals(rank) || Rank.THREE.equals(rank) || Rank.TWO.equals(rank)).findAny().isPresent()) {
                return J_4_3_2;
            }
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.TEN.equals(rank) || Rank.NINE.equals(rank) || Rank.EIGHT.equals(rank) || Rank.SEVEN.equals(rank) || Rank.SIX.equals(rank)).count() == 2l) {
            return DOS_DE_10_9_8_7_6;
        } else if (cards.stream().map(Card::getRank).filter(rank -> Rank.NINE.equals(rank) || Rank.EIGHT.equals(rank) || Rank.SEVEN.equals(rank) || Rank.SIX.equals(rank) || Rank.FIVE.equals(rank)).count() == 2l) {
            return DOS_DE_9_8_7_6_5;
        } else if (cards.stream().map(Card::getSuit).distinct().count() == 1l) {
            return PROSPECT_COLOR;
        }
        return OTRA;
    }

    public String getDescription() {
        return description;
    }
}
