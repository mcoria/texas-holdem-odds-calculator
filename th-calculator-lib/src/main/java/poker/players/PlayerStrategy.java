package poker.players;

import poker.Holdem;
import poker.HoldemListener;
import poker.Player;

public interface PlayerStrategy {

    boolean call(Player player, HoldemListener.HoldemEvent event, Holdem holdem);
}
