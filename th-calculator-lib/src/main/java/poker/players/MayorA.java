package poker.players;

import poker.*;
import poker.analysis.PocketCardsGrouping;

public class MayorA implements PlayerStrategy {


    @Override
    public boolean call(Player player, HoldemListener.HoldemEvent event, Holdem holdem) {
        if (HoldemListener.HoldemEvent.CARTAS_REPARETIDAS.equals(event)) {
            Card[] cards = player.getPocketCards().toArray(new Card[2]);
            PocketCardsGrouping pocketCardsGrouping = new PocketCardsGrouping(cards[0], cards[1]);
            if (Rank.QUEEN.compareTo(pocketCardsGrouping.getMaxRank()) <= 0
                    || (pocketCardsGrouping.isPair() && Rank.NINE.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
                    || (pocketCardsGrouping.isPosibleEscalera() && Rank.JACK.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
                    || (pocketCardsGrouping.isSameColor() && Rank.EIGHT.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
            ) {
                return true;
            }
        }
        return false;
    }
}
