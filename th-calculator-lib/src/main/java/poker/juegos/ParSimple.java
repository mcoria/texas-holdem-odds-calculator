package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;
import java.util.ListIterator;

import static poker.juegos.Juego.Tipo.PAR_SIMPLE;


class ParSimple extends Juego {

    private final Rank parSimple;

    protected ParSimple(Collection<Card> cards, Rank parSimple) {
        super(PAR_SIMPLE, cards);
        this.parSimple = parSimple;
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof ParSimple) {
            ParSimple other = (ParSimple) otherJuego;


            if (this.parSimple.equals(other.parSimple)) {
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
                return this.parSimple.compareTo(other.parSimple);
            }
        }
        return this.type.compareTo(otherJuego.getType());
    }

}
