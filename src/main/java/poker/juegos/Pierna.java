package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;
import java.util.ListIterator;

import static poker.juegos.Juego.Tipo.PIERNA;


class Pierna extends Juego {

    private final Rank pierna;

    protected Pierna(Collection<Card> cards, Rank pierna) {
        super(PIERNA, cards);
        this.pierna = pierna;
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof Pierna) {
            Pierna other = (Pierna) otherJuego;

            if (this.pierna.equals(other.pierna)) {
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
                return this.pierna.compareTo(other.pierna);
            }
        }
        return this.type.compareTo(otherJuego.getType());
    }
}
