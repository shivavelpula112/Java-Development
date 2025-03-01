import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;
        int score = 0;
        
        do {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("I have selected a number between 1 and 100. Try to guess it!");
            
            while (attempts < 10 && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    score += (10 - attempts) + 1; // Higher score for fewer attempts
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + numberToGuess);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);
        
        System.out.println("Your final score is: " + score);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
