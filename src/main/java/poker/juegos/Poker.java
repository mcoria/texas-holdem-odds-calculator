package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;
import java.util.ListIterator;

import static poker.juegos.Juego.Tipo.POKER;


class Poker extends Juego {

    private final Rank poker;

    protected Poker(Collection<Card> cards, Rank poker) {
        super(POKER, cards);
        this.poker = poker;
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof Poker) {
            Poker other = (Poker) otherJuego;
            if (this.poker.equals(other.poker)) {
                ListIterator<Card> iterator = cards.listIterator(5);
                ListIterator<Card> iteratorOther = other.cards.listIterator(5);

                int comparatorResult = 0;
                while (iterator.hasPrevious() && iteratorOther.hasPrevious()) {
                    Card next = iterator.previous();
                    Card nextOther = iteratorOther.previous();

                    comparatorResult = next.getRank().compareTo(nextOther.getRank());

                    if (comparatorResult != 0) {
                        break;
                    }
                }

                return comparatorResult;
            } else {
                return this.poker.compareTo(other.poker);
            }
        }
        return this.type.compareTo(otherJuego.getType());
    }

}
