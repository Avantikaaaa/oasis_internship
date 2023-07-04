import java.util.Random;

import javax.swing.JOptionPane;

public class NumberGuessingGame {

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        int score = 0;
        int rounds = 0;
        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = generateRandomNumber(1, 100);
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (!guessedCorrectly) {
                int guessedNumber = getUserInput("Enter your guess (1-100):");
                attempts++;

                if (guessedNumber == targetNumber) {
                    showMessage("Congratulations! You guessed the number correctly.");
                    guessedCorrectly = true;
                    score += calculateScore(attempts);
                } else if (guessedNumber < targetNumber) {
                    showMessage("Too low! Try again.");
                } else {
                    showMessage("Too high! Try again.");
                }

                if (attempts >= 5) {
                    showMessage("Oops! You've reached the maximum number of attempts.");
                    showMessage("The number was: " + targetNumber);
                    break;
                }
            }

            rounds++;
            playAgain = askToPlayAgain();

            if (!playAgain) {
                showMessage("Game Over! Your final score: " + score);
            }
        }
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static int getUserInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return Integer.parseInt(input);
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static int calculateScore(int attempts) {
        int maxAttempts = 5;
        int scoreMultiplier = 100;
        int baseScore = 1000;

        int remainingAttempts = maxAttempts - attempts;
        int score = baseScore + remainingAttempts * scoreMultiplier;
        return score;
    }

    public static boolean askToPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }
}
