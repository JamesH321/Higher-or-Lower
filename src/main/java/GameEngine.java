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

    public boolean makeGuess(String guess) {
        Card nextCard = deck.draw();

        if (nextCard == null) {
            System.out.println("Game over! The deck is empty!");
            this.gameOver = true;
            return false;
        }

        if (lives == 0) {
            System.out.println("Game over! You are out of lives!");
            this.gameOver = true;
            return false;
        }

        boolean guessHigher = guess.equalsIgnoreCase("H");
        boolean correctGuess = checkGuess(nextCard, guessHigher);

        handleGuess(correctGuess, nextCard);
        this.currentCard = nextCard;

        return correctGuess;
    }

    private boolean checkGuess(Card nextCard, boolean guessHigher) {
        int currentValue = currentCard.rank().getValue();
        int nextValue = nextCard.rank().getValue();

        if (currentValue != -1 || nextValue != -1) {
            if (guessHigher) {
                return nextValue >= currentValue;
            } else {
                return nextValue <= currentValue;
            }
        }

        return true;
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
}
