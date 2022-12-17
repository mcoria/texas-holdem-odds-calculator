package poker.juegos;

import poker.Card;


import java.util.Collection;
import java.util.ListIterator;

import static poker.juegos.Juego.Tipo.COLOR;


class Color extends Juego {

    protected Color(Collection<Card> cards) {
        super(COLOR, cards);
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof poker.juegos.Color) {
            Color other = (Color) otherJuego;

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
        }

        return this.type.compareTo(otherJuego.getType());
    }
}
