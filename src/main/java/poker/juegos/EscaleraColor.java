package poker.juegos;

import poker.Card;
import poker.Rank;

import java.util.Collection;

import static poker.juegos.Juego.Tipo.ESCALERA_COLOR;


class EscaleraColor extends Juego {
    protected EscaleraColor(Collection<Card> cards) {
        super(ESCALERA_COLOR, cards);
    }

    @Override
    public int compareTo(Juego otherJuego) {
        if (otherJuego instanceof EscaleraColor) {
            EscaleraColor other = (EscaleraColor) otherJuego;

            Rank thisMaxRank = getMaxRank(this);
            Rank otherMaxRank = getMaxRank(other);

            return thisMaxRank.compareTo(otherMaxRank);
        }

        return this.type.compareTo(otherJuego.getType());
    }

    private static Rank getMaxRank(EscaleraColor escalera) {
        Rank maxRank = escalera.cards.get(4).getRank();

        if(escalera.cards.stream().map(Card::getRank).filter(rank -> Rank.TWO.equals(rank) || Rank.ACE.equals(rank)).count() == 2l){
            maxRank = Rank.FIVE;
        }

        return maxRank;
    }

}
