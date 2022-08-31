public class Game {

    public static void main(String[] args) {

        // Initialise the word repository

        IRandomiser randomiser = new Randomiser();
        String[] wordList = {"apple", "dog", "cat", "book"};
        IWordRepository wordRepository = new StaticWordRepository(wordList, randomiser);

        // Start the game

        GameEngine engine = new GameEngine(wordRepository, 5);
        while (true) {
            engine.playRound();
        }
        

    }
    
}
