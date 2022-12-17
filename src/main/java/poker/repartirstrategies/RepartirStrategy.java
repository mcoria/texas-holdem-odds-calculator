package poker.repartirstrategies;

import poker.CommonCards;
import poker.Mazo;
import poker.Player;

import java.util.List;

@FunctionalInterface
public interface RepartirStrategy {
    void apply(Mazo mazo, CommonCards commonCards, List<Player> playerCards);
}
