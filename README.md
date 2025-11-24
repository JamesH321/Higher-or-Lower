# Higher or Lower Card Game

A command-line implementation of the classic higher or lower card game, featuring a streak-based scoring system, life
mechanics, and Joker wildcards.

## How to run

Prerequisites: Java 21+

1. Clone the repository.
2. Build the project: `mvn clean package`
3. Run the Jar: `java -jar target/Higher-or-Lower-1.0.jar`

## Game Rules & Features

* Uses a standard deck of 52 cards + 2 Jokers.
* Card order goes from Ace (lowest) to king (highest).
* Points are gained based on the current streak of correct guesses.
* You start with three lives. Wrong guesses lose a life.
* The game ends when you run out of lives.
* Drawing a joker restores one of your lives and gives a 3x multiplier to that round's score.

## Design Decisions

* **MVC Architecture:** Separated logic (`GameEngine`), data (`Deck`, `Card`), and view (`ConsoleRenderer`) to adhere to
  the Single Responsibility Principle.
* **Java Records:** Used for the `Card` class to ensure immutability and reduce boilerplate code.
* **Enums for Type Safety:** Used `Rank` and `Suit` enums to prevent invalid card states, rather than relying on integer
  constants or strings.
* **Rich Console UX:** The application uses ANSI escape codes to render colour-coded text and ASCII art for cards,
  improving the visual experience of the terminal interface.

## Future Improvements

* **Persistent High Scores:** Saving top scores to a local JSON/file.