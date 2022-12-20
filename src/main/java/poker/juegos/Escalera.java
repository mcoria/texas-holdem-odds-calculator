package poker.juegos;

import poker.Card;

import java.util.Collection;

import static poker.juegos.Juego.Tipo.ESCALERA;


class Escalera extends Juego {
    protected Escalera(Collection<Card> cards) {
        super(ESCALERA, cards);
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof Escalera) {
            Escalera other = (Escalera) otherJuego;
            return this.cards.get(0).getRank().compareTo(other.cards.get(0).getRank());
        }

        return this.type.compareTo(otherJuego.type);
    }

}
