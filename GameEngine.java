import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameEngine {

    private IWordRepository wordRepository;
    private int maxLives;
    Scanner scanner = new Scanner(System.in);
    Pattern guessPattern = Pattern.compile("^[a-z]$");
    private static final Character CHAR_BLANK = '*';

    public GameEngine(IWordRepository wordRepository, int lives) {
        this.wordRepository = wordRepository;
        this.maxLives = lives;
    }

    private char getNextGuess() {

        String currentGuess = "";
        boolean valid = false;

        while (!valid) {
            currentGuess = scanner.nextLine();
            Matcher guessMatcher = guessPattern.matcher(currentGuess);
            if (!guessMatcher.find()) {
                System.out.println("Not a valid letter!");
            } else {
                valid = true;
            }
        }

        char[] currentGuessParts = currentGuess.toCharArray();
        char currentGuessChar = currentGuessParts[0];
        return currentGuessChar;
        
    }

    private String generateHiddenWord(char[] currentWordParts, Set<Character> guesses) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < currentWordParts.length; i++) {
            if (guesses.contains(currentWordParts[i])) {
                sb.append(currentWordParts[i]);
            } else {
                sb.append(CHAR_BLANK);
            }
        }

        return sb.toString();

    }

    public boolean playRound() {

        // Reset

        int currentLives = this.maxLives;
        String currentWord = wordRepository.nextWord();
        Set<Character> guesses = new HashSet<Character>();
        char[] currentWordParts = currentWord.toCharArray();

        // Welcome the user to the game

        String welcomeMessage = String.format("Guess the word: %s", generateHiddenWord(currentWordParts, guesses));
        System.out.println(welcomeMessage);

        // Loop until we run out of lives

        do {

            char currentGuess = getNextGuess();

            // Check if the guess is already in the list

            if (guesses.contains(currentGuess)) {
                System.out.println("You have already tried this letter!");
                continue;
            }

            // Add our guess to the list

            guesses.add(currentGuess);

            // Check if our guess was a success
            // Build the display string in the process

            String hiddenWord = generateHiddenWord(currentWordParts, guesses);
            
            // Let the user know if they won the game

            if (!hiddenWord.contains(CHAR_BLANK.toString())) {
                String winMessage = String.format("You have won the game. The word was ‘%s’.", currentWord);
                System.out.println(winMessage);
                return true;
            }

            // Handle the successful and unsuccessful guesses

            if (currentWord.indexOf(currentGuess) >= 0) {
                String successMessage = String.format("Correct. %s", hiddenWord);
                System.out.println(successMessage);
            } else {
                currentLives -= 1;
                String failureMessage = String.format("Incorrect 1 life lost. %d remaining. The current word is %s", currentLives, hiddenWord);
                System.out.println(failureMessage);
            }

        } while (currentLives > 0);

        System.out.println("Hard luck! Why not try again?");
        return false;

    }
    
}
