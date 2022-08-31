import java.util.ArrayList;
import java.util.Arrays;

public class StaticWordRepository implements IWordRepository {

    private ArrayList<String> randomisedWords;

    public StaticWordRepository(String[] words, IRandomiser randomiser) {
        ArrayList<String> convertedWords = new ArrayList<String>(Arrays.asList(words));
        this.randomisedWords = randomiser.randomise(convertedWords);
    }

    @Override
    public String nextWord() {
        String nextWord = this.randomisedWords.remove(0);
        this.randomisedWords.add(nextWord);
        return nextWord;
    }
    
}
