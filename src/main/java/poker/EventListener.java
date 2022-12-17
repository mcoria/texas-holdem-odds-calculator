package poker;

public interface EventListener {

    enum HoldemEvents {NEW_GAME, CARTAS_REPARETIDAS, FLOP, CUARTA_CARTA, QUINTA_CARTA, FINISHED}

    void raiseEvent(HoldemEvents event, Holdem holdem);

    void printStatics();
}
