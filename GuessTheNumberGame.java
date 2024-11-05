import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int MAX_ROUNDS = 5;     // Total number of rounds
    private static final int MAX_ATTEMPTS = 7;   // Max attempts per round
    private static final int RANGE = 100;        // Range of numbers to guess (1 to 100)
    private static int totalScore = 0;           // Tracks the user's total score
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Guess the Number Game!");

        // Play multiple rounds
        for (int round = 1; round <= MAX_ROUNDS; round++) {
            System.out.println("\n--- Round " + round + " ---");
            int roundScore = playRound(scanner);
            totalScore += roundScore;
            System.out.println("Score for Round " + round + ": " + roundScore);
            System.out.println("Total Score: " + totalScore);
        }
        
        // Game over
        System.out.println("\nGame Over! Your Total Score: " + totalScore);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
    
    // Method to play a single round
    private static int playRound(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(RANGE) + 1;  // Generate a number between 1 and 100
        int attempts = 0;                               // Track attempts in the round
        int roundScore = 0;                             // Track score for this round
        boolean isCorrect = false;

        System.out.println("Guess the number between 1 and " + RANGE);
        
        // Loop to allow multiple attempts
        while (attempts < MAX_ATTEMPTS && !isCorrect) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == targetNumber) {
                System.out.println("Congratulations! You've guessed it right.");
                roundScore = calculateScore(attempts);  // Calculate points based on attempts
                isCorrect = true;
            } else {
                // Provide hints based on how close the guess is
                giveHint(guess, targetNumber);
            }
        }
        
        if (!isCorrect) {
            System.out.println("Out of attempts! The correct number was: " + targetNumber);
        }
        
        return roundScore;
    }
    
    // Method to calculate score based on the number of attempts
    private static int calculateScore(int attempts) {
        switch (attempts) {
            case 1: return 50;    // Maximum points for guessing on the first attempt
            case 2: return 40;
            case 3: return 30;
            case 4: return 20;
            case 5: return 15;
            case 6: return 10;
            case 7: return 5;     // Minimum points if guessed on the last attempt
            default: return 0;    // No points if failed to guess within attempts
        }
    }
    
    // Method to give hints based on the difference between guess and target number
    private static void giveHint(int guess, int targetNumber) {
        int difference = Math.abs(guess - targetNumber);

        if (difference >= 30) {
            System.out.println("You're very far off.");
        } else if (difference >= 20) {
            System.out.println("You're far off.");
        } else if (difference >= 10) {
            System.out.println("You're close!");
        } else {
            System.out.println("You're very close!");
        }

        if (guess < targetNumber) {
            System.out.println("The number is higher! Try again.");
        } else {
            System.out.println("The number is lower! Try again.");
        }
    }
}
