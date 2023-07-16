package poker.players;

import poker.Card;
import poker.DeckOfCards;
import poker.Player;

import java.util.Set;

public class PlayerWithCards extends Player {

    public PlayerWithCards setPocketCards(Card card1, Card card2) {
        pocketCards.clear();
        pocketCards.add(card1);
        pocketCards.add(card2);
        return this;
    }

    @Override
    public void receiveRandomCards(DeckOfCards deckOfCards) {
    }

    @Override
    public void pocketCardsReset() {
    }

    @Override
    public void collectCardsToAvoid(DeckOfCards deckOfCards) {
        deckOfCards.addCardsToAvoid(pocketCards);
    }

}
