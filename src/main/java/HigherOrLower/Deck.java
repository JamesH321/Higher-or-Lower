package HigherOrLower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                if (rank != Rank.JOKER && suit != Suit.NONE) {
                    cards.add(new Card(rank, suit));
                }
            }
        }

        cards.add(new Card(Rank.JOKER, Suit.NONE));
        cards.add(new Card(Rank.JOKER, Suit.NONE));
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        if (this.cards.isEmpty()) {
            return null;
        }

        return this.cards.removeFirst();
    }

    public int cardsRemaining() {
        return cards.size();
    }
}
