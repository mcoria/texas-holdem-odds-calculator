package poker.juegos;

import poker.Card;

import java.util.Collection;

import static poker.juegos.Juego.Tipo.POKER;


class Poker extends Juego {
    protected Poker(Collection<Card> cards) {
        super(POKER, cards);
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof poker.juegos.Poker) {
            poker.juegos.Poker other = (poker.juegos.Poker) otherJuego;
            return this.cards.get(0).getRank().compareTo(other.cards.get(0).getRank());
        }

        return this.type.compareTo(otherJuego.getType());
    }

}
