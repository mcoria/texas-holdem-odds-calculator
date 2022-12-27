package poker.juegos;

import poker.Card;
import poker.Rank;

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

            Rank thisMaxRank = getMaxRank(this);
            Rank otherMaxRank = getMaxRank(other);

            return thisMaxRank.compareTo(otherMaxRank);
        }

        return this.type.compareTo(otherJuego.type);
    }

    private static Rank getMaxRank(Escalera escalera) {
        Rank maxRank = escalera.cards.get(4).getRank();

        if(escalera.cards.stream().map(Card::getRank).filter(rank -> Rank.TWO.equals(rank) || Rank.ACE.equals(rank)).count() == 2l){
            maxRank = Rank.FIVE;
        }

        return maxRank;
    }

}
