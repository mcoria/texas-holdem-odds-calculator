package poker;

public interface HoldemListener {

    enum HoldemEvents {NEW_GAME, CARTAS_REPARETIDAS, FLOP, TURN, RIVER, FINISHED_ABANDONO, FINISHED}

    void catchEvent(HoldemEvents event, Holdem holdem);

}
