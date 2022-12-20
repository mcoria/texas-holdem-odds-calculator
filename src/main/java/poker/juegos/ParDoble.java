package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;
import java.util.ListIterator;

import static poker.juegos.Juego.Tipo.PAR_DOBLE;


class ParDoble extends Juego {

    private final Rank parMax;

    private final Rank parMin;

    protected ParDoble(Collection<Card> cards, Rank parMax, Rank parMin) {
        super(PAR_DOBLE, cards);
        this.parMax = parMax;
        this.parMin = parMin;
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof ParDoble) {
            ParDoble other = (ParDoble) otherJuego;


            if (this.parMax.equals(other.parMax)) {
                if (this.parMin.equals(other.parMin)) {
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
                    return this.parMin.compareTo(other.parMin);
                }
            } else {
                return this.parMax.compareTo(other.parMax);
            }
        }
        return this.type.compareTo(otherJuego.getType());
    }
}
