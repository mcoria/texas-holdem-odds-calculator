package poker;

public interface HoldemListener {

    enum HoldemEvent {NEW_GAME, CARTAS_REPARETIDAS, FLOP, TURN, RIVER, FINISHED_ABANDONO, FINISHED}

    void catchEvent(HoldemEvent event, Holdem holdem);

}
