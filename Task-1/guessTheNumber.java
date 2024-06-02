import java.util.Random;
import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int min = 1;
        final int max = 100;
        final int maxAttempts = 10; // Limit the number of attempts per round
        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("Welcome to Guess the Number!");

        while (playAgain) {
            int numberToGuess = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("I have generated a number between " + min + " and " + max + ". Try to guess it!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1); // Points based on the number of attempts
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Your current score is: " + totalScore);

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Game over! Your total score is: " + totalScore);
        scanner.close();
    }
}
