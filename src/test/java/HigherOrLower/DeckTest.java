package HigherOrLower;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void testDeckInitialisation() {
        Deck deck = new Deck();
        assertEquals(54, deck.cardsRemaining(), "Deck should start with 54 cards.");
    }

    @Test
    void testDeckContainsJokers() {
        Deck deck = new Deck();

        // Draw all cards to search for a Joker
        int numOfJokers = 0;
        while (deck.cardsRemaining() > 0) {
            Card card = deck.draw();
            if (card.rank() == Rank.JOKER) {
                numOfJokers += 1;
            }
        }
        assertEquals(2, numOfJokers, "New deck must contain at least one Joker.");
    }

    @Test
    void testDraw() {
        Deck deck = new Deck();
        int initialSize = deck.cardsRemaining();

        Card drawn = deck.draw();

        assertNotNull(drawn, "Draw should return a card.");
        assertEquals(initialSize - 1, deck.cardsRemaining(), "Deck size should decrease by 1 after drawing.");
    }

    @Test
    void testDrawEmptyDeckReturnsNull() {
        Deck deck = new Deck();
        // Empty the deck
        while (deck.cardsRemaining() > 0) {
            deck.draw();
        }

        assertNull(deck.draw(), "Drawing from an empty deck should return null.");
    }
}