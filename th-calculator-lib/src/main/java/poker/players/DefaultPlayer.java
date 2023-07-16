package poker.players;

import poker.DeckOfCards;
import poker.Player;

public class DefaultPlayer extends Player {

    @Override
    public void receiveRandomCards(DeckOfCards deckOfCards) {
        pocketCards.add(deckOfCards.getRandomCard());
        pocketCards.add(deckOfCards.getRandomCard());
    }

    @Override
    public void pocketCardsReset() {
        pocketCards.clear();
    }

    @Override
    public void collectCardsToAvoid(DeckOfCards deckOfCards) {
    }
}
