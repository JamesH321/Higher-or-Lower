package HigherOrLower;

import java.util.Scanner;

public class GameEngine {
    private final Deck deck;
    private Card currentCard;
    private int score;
    private int streak;
    private int lives;
    private boolean gameOver;

    public GameEngine() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.score = 0;
        this.streak = 0;
        this.lives = 3;
        this.gameOver = false;

        this.currentCard = this.deck.draw();
    }

    public static void main(String[] args) {
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        Scanner scanner = new Scanner(System.in);

        GameEngine gameEngine = new GameEngine();
        consoleRenderer.displayWelcome();

        while (!gameEngine.isGameOver()) {
            consoleRenderer.displayGameState(
                    gameEngine.getCurrentCard(),
                    gameEngine.getScore(),
                    gameEngine.getStreak(),
                    gameEngine.getLives(),
                    gameEngine.cardsRemaining()
            );

            boolean validInput = false;
            String input = "";
            while (!validInput) {
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("H") || input.equals("L")) {
                    validInput = true;
                } else {
                    System.out.print("Invalid input. Please try again > ");
                }
            }

            boolean correctGuess = gameEngine.makeGuess(input);

            if (correctGuess) {
                System.out.print("\nCorrect!\n");
            } else {
                System.out.print("\nIncorrect!\n");

            }
        }

        consoleRenderer.displayGameOver(gameEngine.getScore());
    }

    public boolean makeGuess(String guess) {
        Card nextCard = deck.draw();

        boolean guessHigher = guess.equalsIgnoreCase("H");
        boolean correctGuess = checkGuess(nextCard, guessHigher);

        handleGuess(correctGuess, nextCard);
        this.currentCard = nextCard;

        if (deck.cardsRemaining() == 0 || lives == 0) {
            this.gameOver = true;
        }

        return correctGuess;
    }

    private void handleGuess(boolean correctGuess, Card nextCard) {
        int jokerMultiplier = 1;
        if (correctGuess) {
            this.streak += 1;
            if (nextCard.rank().getValue() == -1) {
                this.lives += 1;
                jokerMultiplier = 3;
            }
        } else {
            this.lives -= 1;
            this.streak = 0;
        }

        this.score += this.streak * jokerMultiplier;
    }

    public int getScore() {
        return score;
    }

    public int getStreak() {
        return streak;
    }

    public int getLives() {
        return lives;
    }

    private boolean checkGuess(Card nextCard, boolean guessHigher) {
        int currentValue = currentCard.rank().getValue();
        int nextValue = nextCard.rank().getValue();

        if (currentValue == -1 || nextValue == -1) {
            return true;
        }

        if (guessHigher) {
            return nextValue >= currentValue;
        }

        return nextValue <= currentValue;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public int cardsRemaining() {
        return deck.cardsRemaining();
    }
}
