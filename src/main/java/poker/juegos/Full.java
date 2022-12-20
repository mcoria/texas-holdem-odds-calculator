package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;

import static poker.juegos.Juego.Tipo.FULL;


class Full extends Juego {

    private final Rank parMax;

    private final Rank parMin;

    protected Full(Collection<Card> cards, Rank parMax, Rank parMin) {
        super(FULL, cards);
        this.parMax = parMax;
        this.parMin = parMin;
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof Full) {
            Full other = (Full) otherJuego;

            if (this.parMax.equals(other.parMax)) {
                return this.parMin.compareTo(other.parMin);
            } else {
                return this.parMax.compareTo(other.parMax);
            }
        }
        return this.type.compareTo(otherJuego.getType());
    }

}
