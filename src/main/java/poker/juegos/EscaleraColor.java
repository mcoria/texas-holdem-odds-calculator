package poker.juegos;

import poker.Card;


import java.util.Collection;

import static poker.juegos.Juego.Tipo.ESCALERA_COLOR;


class EscaleraColor extends Juego {
    protected EscaleraColor(Collection<Card> cards) {
        super(ESCALERA_COLOR, cards);
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof poker.juegos.EscaleraColor) {
            poker.juegos.EscaleraColor other = (poker.juegos.EscaleraColor) otherJuego;
            return this.cards.get(0).getRank().compareTo(other.cards.get(0).getRank());
        }

        return this.type.compareTo(otherJuego.getType());
    }

}
