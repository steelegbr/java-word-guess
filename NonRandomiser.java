import java.util.ArrayList;

public class NonRandomiser implements IRandomiser {
    public ArrayList<String> randomise(ArrayList<String> words) {
        ArrayList<String> shuffled = new ArrayList<String>(words);
        return shuffled;
    }
}
