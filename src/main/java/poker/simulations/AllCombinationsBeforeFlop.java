package poker.simulations;

import poker.*;
import poker.EventListener;
import poker.juegos.Juego;
import poker.listeners.HoldemStatics;

import java.util.*;

public class AllCombinationsBeforeFlop extends AbstractSimulationReport {

    private static final int SIMULATIONS = 1000000;

    private static final int PLAYERS = 8; //23 Max con un mazo

    public static void main(String[] args) {
        new AllCombinationsBeforeFlop().simulate();
    }

    @Override
    protected int getNumberOfSimulations() {
        return SIMULATIONS;
    }

    private Player observer = new Player();


    @Override
    protected List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(observer);

        for (int i = 0; i < PLAYERS - 1; i++) {
            players.add(new Player());
        }
        return players;
    }

    @Override
    protected CommonCards createCommonCards() {
        return new CommonCards();
    }

    @Override
    protected List<HoldemStatics> setupEventListeners() {
        return List.of(new HoldemStatics() {
            private int observerGana = 0;

            private Map<TipoCombinacion, Integer> contadorJuego = new HashMap<>();
            private Map<TipoCombinacion, Integer> contadorJuegoGanador = new HashMap<>();

            @Override
            public void catchEvent(HoldemEvents event, Holdem holdem) {
                if (event.equals(HoldemEvents.FINISHED)) {
                    boolean ganador = false;
                    if (holdem.getGanadores().contains(observer)) {
                        observerGana++;
                        ganador = true;
                    }

                    TipoCombinacion combinacion = TipoCombinacion.loadTipo(observer.getCards());

                    contadorJuego.compute(combinacion, (k, v) -> v == null ? 1 : v + 1);
                    if (ganador) {
                        contadorJuegoGanador.compute(combinacion, (k, v) -> v == null ? 1 : v + 1);
                    }

                }
            }

            @Override
            public void printStatics() {
                System.out.println("Probabilidad de ganar = \t\t\t" + String.format("%3.2f%%", 100f * (float) observerGana / (float) SIMULATIONS));

                for (TipoCombinacion combinacion :
                        TipoCombinacion.values()) {

                    if (contadorJuego.containsKey(combinacion)) {
                        int contador = contadorJuego.get(combinacion);
                        int contadorGanador = contadorJuegoGanador.getOrDefault(combinacion, 0);

                        System.out.println("Probabilidad de ganar con '" + combinacion.getDescription() + "' = \t\t\t" + String.format("%3.2f%%", 100f * (float) contadorGanador / (float) contador));
                    }
                }
            }
        });
    }

    protected enum TipoCombinacion {
        PAR_As("par de As"),
        PAR_K("par de Ks"),
        PAR_Q("par de Qs"),
        PAR_J("par de Js"),
        PAR_10("par de 10s"),
        PAR_9("par de 9s"),
        PAR_8("par de 8s"),
        PAR_7("par de 7s"),
        PAR_6("par de 6s"),
        PAR_5("par de 5s"),
        PAR_4("par de 4s"),
        PAR_3("par de 3s"),
        PAR_2("par de 2s"),

        PROSPECT_COLOR("prospect color"),

        A_K("A y K"),

        OTRA("otra");

        private final String description;

        TipoCombinacion(String description) {
            this.description = description;
        }

        static TipoCombinacion loadTipo(Set<Card> cards) {
            // Detectar par
            if (cards.stream().map(Card::getRank).distinct().count() == 1l) {
                Card theCard = cards.stream().findAny().get();
                switch (theCard.getRank()) {
                    case ACE:
                        return PAR_As;
                    case KING:
                        return PAR_K;
                    case QUEEN:
                        return PAR_Q;
                    case JACK:
                        return PAR_J;
                    case TEN:
                        return PAR_10;
                    case NINE:
                        return PAR_9;
                    case EIGHT:
                        return PAR_8;
                    case SEVEN:
                        return PAR_7;
                    case SIX:
                        return PAR_6;
                    case FIVE:
                        return PAR_5;
                    case FOUR:
                        return PAR_4;
                    case THREE:
                        return PAR_3;
                    case TWO:
                        return PAR_2;
                }
            } else if (cards.stream().map(Card::getSuit).distinct().count() == 1l) {
                return PROSPECT_COLOR;
            } else {
                if (cards.stream().map(Card::getRank).filter(rank -> Rank.ACE.equals(rank)  || Rank.KING.equals(rank)).count() == 2L) {
                    return A_K;
                }
            }
            return OTRA;
        }

        public String getDescription() {
            return description;
        }
    }
}
