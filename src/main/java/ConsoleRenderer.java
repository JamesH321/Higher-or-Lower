public class ConsoleRenderer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m"; // For jokers
    private static final String ANSI_ORANGE = "\u001B[38;5;208m"; // For UI
    // For card borders

    public static void main(String[] args) {
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();
        consoleRenderer.displayWelcome();
        consoleRenderer.displayGameState(new Card(Rank.EIGHT, Suit.HEARTS), 10, 5, 1);
        consoleRenderer.displayGameState(new Card(Rank.TEN, Suit.DIAMONDS), 10, 5, 1);
        consoleRenderer.displayGameState(new Card(Rank.KING, Suit.CLUBS), 10, 5, 1);
        consoleRenderer.displayGameState(new Card(Rank.QUEEN, Suit.SPADES), 10, 5, 1);
        consoleRenderer.displayGameState(new Card(Rank.JOKER, Suit.NONE), 10, 5, 1);
        consoleRenderer.displayGameOver(100);
    }

    public void displayWelcome() {
        System.out.println(ANSI_ORANGE + "======================================");
        System.out.println("      WELCOME TO HIGHER OR LOWER      ");
        System.out.println("======================================" + ANSI_RESET);
    }

    public void displayGameState(Card card, int score, int streak, int lives) {
        System.out.println("\n---------------------------------------");
        System.out.println(" SCORE: " + ANSI_ORANGE + score + ANSI_RESET +
                "  |  STREAK: " + ANSI_ORANGE + "x" + streak + ANSI_RESET +
                "  |  LIVES: " + ANSI_ORANGE + lives + ANSI_RESET);
        System.out.println("---------------------------------------\n");

        System.out.println("Current Card:");
        printAsciiCard(card);

        System.out.print("\nWill the next card be (H)igher or (L)ower? > ");
    }

    public void displayGameOver(int finalScore) {
        System.out.println(ANSI_RED + "\n=== GAME OVER ===" + ANSI_RESET);
        System.out.println("Final Score: " + ANSI_ORANGE + finalScore + ANSI_RESET);
        System.out.println("Thank you for playing!");
    }

    private void printAsciiCard(Card card) {
        String colour;
        if (card.suit() == Suit.HEARTS || card.suit() == Suit.DIAMONDS) {
            colour = ANSI_RED;
        } else if (card.suit() == Suit.SPADES || card.suit() == Suit.CLUBS) {
            colour = ANSI_CYAN;
        } else {
            colour = ANSI_YELLOW;
        }

        String rankString = getRankString(card);
        String spaceBetweenRank = rankString.length() == 2 ? "    " : "     ";

        System.out.println(".-------.");
        System.out.println("| " + colour + rankString + ANSI_RESET + spaceBetweenRank + "|");
        System.out.println("|   " + colour + card.suit().getSymbol() + ANSI_RESET + "   |");
        System.out.println("|" + spaceBetweenRank + colour + rankString + ANSI_RESET + " |");
        System.out.println("'-------'");
    }

    private String getRankString(Card card) {
        int value = card.rank().getValue();
        return switch (value) {
            case 1 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            case -1 -> "JK";
            default -> String.valueOf(value);
        };
    }
}