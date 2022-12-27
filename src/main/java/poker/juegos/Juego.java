package poker.juegos;

import poker.Card;
import poker.Rank;
import poker.Suit;

import java.util.*;
import java.util.stream.Collectors;

import static poker.Rank.*;

public abstract class Juego implements Comparable<Juego> {

    public enum Tipo {NADA, PAR_SIMPLE, PAR_DOBLE, PIERNA, ESCALERA, COLOR, FULL, POKER, ESCALERA_COLOR}

    private final static List<Set<Rank>> escalerasPosibles = List.of(
            Set.of(ACE, KING, QUEEN, JACK, TEN),
            Set.of(KING, QUEEN, JACK, TEN, NINE),
            Set.of(QUEEN, JACK, TEN, NINE, EIGHT),
            Set.of(JACK, TEN, NINE, EIGHT, SEVEN),
            Set.of(TEN, NINE, EIGHT, SEVEN, SIX),
            Set.of(NINE, EIGHT, SEVEN, SIX, FIVE),
            Set.of(EIGHT, SEVEN, SIX, FIVE, FOUR),
            Set.of(SEVEN, SIX, FIVE, FOUR, THREE),
            Set.of(SIX, FIVE, FOUR, THREE, TWO),
            Set.of(FIVE, FOUR, THREE, TWO, ACE)
    );

    protected final Tipo type;
    protected final List<Card> cards;

    protected Juego(Tipo type, Collection<Card> cards) {
        this.type = type;
        this.cards = cards.stream().sorted(Comparator.comparing(Card::getRank)).collect(Collectors.toList());
    }

    public static Juego cargarJuego(Collection<Card> cards) {
        if (cards.size() > 7 || cards.size() < 5) {
            throw new RuntimeException("Invalid game collection");
        }

        Map<Suit, Set<Card>> groupBySuit = new HashMap<>();
        Map<Rank, Set<Card>> groupByValue = new HashMap<>();

        Suit colorSuite = null;

        Set<Rank> paresSimples = new HashSet<>();

        Set<Rank> piernas = new HashSet<>();

        Rank pokerRank = null;

        for (Card card : cards) {
            Set<Card> suiteGroup = groupBySuit.compute(card.getSuit(), (k, v) -> v == null ? new HashSet<>() : v);
            suiteGroup.add(card);
            if (suiteGroup.size() == 5) {
                colorSuite = card.getSuit();
            }

            Set<Card> valueGroup = groupByValue.compute(card.getRank(), (k, v) -> v == null ? new HashSet<>() : v);
            valueGroup.add(card);
            if (valueGroup.size() == 2) {
                paresSimples.add(card.getRank());
            } else if (valueGroup.size() == 3) {
                paresSimples.remove(card.getRank());
                piernas.add(card.getRank());
            } else if (valueGroup.size() == 4) {
                piernas.remove(card.getRank());
                pokerRank = card.getRank();
            }
        }


        Juego juego = null;

        // Escalera Color
        if (colorSuite != null) {
            Suit finalColorSuite = colorSuite;
            Collection<Card> escaleraCards = getEscalera(cards.parallelStream().filter(theCard -> finalColorSuite.equals(theCard.getSuit())).collect(Collectors.toSet()));
            if (escaleraCards != null) {
                juego = new EscaleraColor(escaleraCards);
            }
        }

        // Poker
        if (juego == null && pokerRank != null) {
            Rank finalPokerRank = pokerRank;
            List<Card> theCards = new ArrayList<>();

            theCards.addAll(groupByValue.get(pokerRank));

            theCards.add(cards.stream()
                    .filter(theCard -> !finalPokerRank.equals(theCard.getRank()))
                    .sorted(Comparator.comparing(Card::getRank).reversed())
                    .limit(1)
                    .findAny().get()
            );

            juego = new Poker(theCards);
        }

        // Full
        if (juego == null && piernas.size() > 0 && paresSimples.size() > 0) {
            List<Card> theCards = new ArrayList<>();

            Rank piernaRank = piernas.stream().sorted(Comparator.reverseOrder()).limit(1).findAny().get();

            // Pierna - 3 cartas
            theCards.addAll(groupByValue.get(piernaRank));


            Rank parRank = paresSimples.stream().sorted(Comparator.reverseOrder()).limit(1).findAny().get();

            // Par - 2 cartas
            theCards.addAll(groupByValue.get(parRank));


            juego = new Full(theCards, piernaRank, parRank);
        }

        // Color
        if (juego == null && colorSuite != null) {
            juego = new Color(groupBySuit.get(colorSuite).stream().sorted(Comparator.comparing(Card::getRank).reversed()).limit(5).collect(Collectors.toList()));
        }


        // Escalera
        if (juego == null) {
            Collection<Card> escaleraCards = getEscalera(cards);
            if (escaleraCards != null) {
                juego = new Escalera(escaleraCards);
            }
        }

        // Pierna
        if (juego == null && piernas.size() > 0) {
            List<Card> theCards = new ArrayList<>();

            Rank piernaRank = piernas.stream().sorted(Comparator.reverseOrder()).limit(1).findAny().get();

            // Pierna - 3 cartas
            theCards.addAll(groupByValue.get(piernaRank));

            // 2 cartas que no sean del par
            // y sean las mayores
            theCards.addAll(cards.stream()
                    .filter(theCard -> !piernaRank.equals(theCard.getRank()))
                    .sorted(Comparator.comparing(Card::getRank).reversed())
                    .limit(2)
                    .collect(Collectors.toList())
            );

            juego = new Pierna(theCards, piernaRank);

        }

        // Doble par
        if (juego == null && paresSimples.size() > 1) {
            List<Card> theCards = new ArrayList<>();

            List<Rank> maxValuesPair = paresSimples.stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());

            // 2 pares - 4 cartas
            for (Rank rankPair : maxValuesPair) {
                theCards.addAll(groupByValue.get(rankPair));
            }


            // 3 cartas que no sean del par
            // y sean las mayores
            theCards.addAll(cards.stream()
                    .filter(theCard -> !maxValuesPair.contains(theCard.getRank()))
                    .sorted(Comparator.comparing(Card::getRank).reversed())
                    .limit(1)
                    .collect(Collectors.toList())
            );

            juego = new ParDoble(theCards, maxValuesPair.get(0), maxValuesPair.get(1));

        }

        // Par simple
        if (juego == null && paresSimples.size() > 0) {
            List<Card> theCards = new ArrayList<>();

            Rank parRank = paresSimples.stream().sorted(Comparator.reverseOrder()).limit(1).findAny().get();

            // Par - 2 cartas
            theCards.addAll(groupByValue.get(parRank));

            // 3 cartas que no sean del par
            // y sean las mayores
            theCards.addAll(cards.stream()
                    .filter(theCard -> !parRank.equals(theCard.getRank()))
                    .sorted(Comparator.comparing(Card::getRank).reversed())
                    .limit(3)
                    .collect(Collectors.toList())
            );

            juego = new ParSimple(theCards, parRank);
        }


        return juego == null ? new Nada(cards.stream().sorted(Comparator.comparing(Card::getRank).reversed()).limit(5).collect(Collectors.toList())) : juego;
    }


    private static Collection<Card> getEscalera(Collection<Card> cards) {
        Set<Card> result = null;
        Set<Rank> distinctRanks = cards.stream().map(card -> card.getRank()).collect(Collectors.toSet());
        if (distinctRanks.size() >= 5) {
            for (Set<Rank> escaleraPosible : escalerasPosibles) {
                if (distinctRanks.containsAll(escaleraPosible)) {
                    result = escaleraPosible.stream()
                            .map(value -> cards
                                    .stream()
                                    .filter(theCard -> value.equals(theCard.getRank()))
                                    .findAny().get())
                            .collect(Collectors.toSet());
                    break;
                }
            }
        }
        return result;
    }

    public Tipo getType() {
        return type;
    }

    public Set<Card> getCards() {
        return cards.stream().collect(Collectors.toSet());
    }


    @Override
    public String toString() {
        return String.format("Tipo={%s}; Cards=%s", type.toString(), cards.toString());
    }
}
