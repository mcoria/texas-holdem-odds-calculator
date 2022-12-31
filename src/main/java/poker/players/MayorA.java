package poker.players;

import poker.*;
import poker.simulations.PocketCardsGrouping;

public class MayorA extends Player {

    @Override
    public void reset() {
        super.reset();
        defaultCallResponse = false;
    }

    @Override
    public boolean call(EventListener.HoldemEvents stage, int playersInGame, CommunityCards communityCards) {
        if (EventListener.HoldemEvents.CARTAS_REPARETIDAS.equals(stage)) {
            Card[] cards = getPocketCards().toArray(new Card[2]);
            PocketCardsGrouping pocketCardsGrouping = new PocketCardsGrouping(cards[0], cards[1]);
            if (Rank.QUEEN.compareTo(pocketCardsGrouping.getMaxRank()) <= 0 || pocketCardsGrouping.isPair()) {
                setDefaultCallResponse(true);
            }
        }
        return defaultCallResponse;
    }
}
