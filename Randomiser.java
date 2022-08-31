import java.util.ArrayList;
import java.util.Collections;

public class Randomiser implements IRandomiser {
    public ArrayList<String> randomise(ArrayList<String> words) {
        ArrayList<String> shuffled = new ArrayList<String>(words);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}
