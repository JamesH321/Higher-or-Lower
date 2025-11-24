package HigherOrLower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private GameEngine game;

    @BeforeEach
    void setUp() {
        game = new GameEngine();
    }

    @Test
    void testCorrectGuessIncreasesScoreAndStreak() {
        injectCurrentCard(game, new Card(Rank.TWO, Suit.CLUBS));
        injectNextCard(game, new Card(Rank.TEN, Suit.HEARTS));

        boolean result = game.makeGuess("H");

        assertTrue(result, "Guessing Higher on 2 -> 10 should be correct.");
        assertEquals(1, game.getStreak(), "Streak should increase to 1.");
        assertEquals(1, game.getScore(), "Score should be 1.");
    }

    @Test
    void testIncorrectGuessLosesLifeAndResetsStreak() {
        injectCurrentCard(game, new Card(Rank.TEN, Suit.CLUBS));
        injectNextCard(game, new Card(Rank.TWO, Suit.HEARTS));

        int initialLives = game.getLives();

        boolean result = game.makeGuess("H");

        assertFalse(result, "Guessing Higher on 10 -> 2 should be wrong.");
        assertEquals(0, game.getStreak(), "Streak should reset to 0.");
        assertEquals(initialLives - 1, game.getLives(), "Lives should decrease by 1.");
    }

    @Test
    void testJokerGivesMultiplierAndRestoresLife() {
        injectCurrentCard(game, new Card(Rank.TEN, Suit.CLUBS));
        injectNextCard(game, new Card(Rank.TWO, Suit.HEARTS));
        game.makeGuess("H");

        injectNextCard(game, new Card(Rank.JOKER, Suit.NONE));

        assertEquals(2, game.getLives());
        int scoreBefore = game.getScore();

        boolean result = game.makeGuess("H");

        assertTrue(result, "Joker should always be a correct guess.");
        assertEquals(3, game.getLives(), "Joker should restore a life.");
        assertEquals(scoreBefore + 3, game.getScore(), "Score should increase with 3x multiplier.");
    }

    @Test
    void testTieIsWin() {
        injectCurrentCard(game, new Card(Rank.SEVEN, Suit.CLUBS));
        injectNextCard(game, new Card(Rank.SEVEN, Suit.DIAMONDS));

        boolean result = game.makeGuess("H");

        assertTrue(result, "Guessing Higher on a tie should be a win");
    }

    private void injectCurrentCard(GameEngine instance, Card card) {
        try {
            Field field = GameEngine.class.getDeclaredField("currentCard");
            field.setAccessible(true);
            field.set(instance, card);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    private void injectNextCard(GameEngine instance, Card card) {
        try {
            Field deckField = GameEngine.class.getDeclaredField("deck");
            deckField.setAccessible(true);
            Deck deck = (Deck) deckField.get(instance);

            Field cardsField = Deck.class.getDeclaredField("cards");
            cardsField.setAccessible(true);
            List<Card> cards = (List<Card>) cardsField.get(deck);

            cards.addFirst(card);

        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }
}