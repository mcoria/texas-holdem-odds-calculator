package poker.players;

import poker.*;
import poker.analysis.PocketCardsGrouping;

public class MayorA extends Player {

    @Override
    public void reset() {
        super.reset();
        defaultCallResponse = false;
    }

    @Override
    public boolean call(HoldemListener.HoldemEvents stage, int playersInGame, CommunityCards communityCards) {
        if (HoldemListener.HoldemEvents.CARTAS_REPARETIDAS.equals(stage)) {
            Card[] cards = getPocketCards().toArray(new Card[2]);
            PocketCardsGrouping pocketCardsGrouping = new PocketCardsGrouping(cards[0], cards[1]);
            if (Rank.QUEEN.compareTo(pocketCardsGrouping.getMaxRank()) <= 0
                    || (pocketCardsGrouping.isPair() && Rank.NINE.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
                    || (pocketCardsGrouping.isPosibleEscalera() && Rank.JACK.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
                    || (pocketCardsGrouping.isSameColor() && Rank.EIGHT.compareTo(pocketCardsGrouping.getMaxRank()) <= 0)
            ) {
                setDefaultCallResponse(true);
            }
        }
        return defaultCallResponse;
    }
}
