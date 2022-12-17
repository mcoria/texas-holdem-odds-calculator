package poker.repartirstrategies;

import poker.CommonCards;
import poker.Mazo;
import poker.Player;

import java.util.List;

public class Default implements RepartirStrategy {
    @Override
    public void apply(Mazo mazo, CommonCards commonCards, List<Player> playerCards) {
        commonCards.receiveCards(mazo);
        for (Player player : playerCards) {
            player.receiveCards(mazo);
        }
    }
}
