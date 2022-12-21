package poker;

public interface EventListener {

    enum HoldemEvents {NEW_GAME, CARTAS_REPARETIDAS, FLOP, TURN, RIVER, FINISHED}

    void catchEvent(HoldemEvents event, Holdem holdem);

    void printStatics();
}
