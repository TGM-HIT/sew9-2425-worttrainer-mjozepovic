import java.util.ArrayList;

public class WordCoach {

    private int right;
    private int wrong;
    private int total;

    ArrayList<WordPair> wordPairs = new ArrayList<>();
    WordPair currentWordPair;



    public WordCoach() {

    }

    public void chooseCurrentWordPair(int index) {
        for(int i = 0; i < wordPairs.size(); i++) {
            this.currentWordPair = wordPairs.get(i);
        }
    }

    public void chooseCurrentWordPair() {
        this.currentWordPair = wordPairs.get((int) (Math.random() * wordPairs.size()));
    }


    public boolean checkWordPair(String guessedWord) {

        if(this.currentWordPair.getWord().equalsIgnoreCase(guessedWord)) {
            chooseCurrentWordPair();
            this.right++;
            this.total++;
            return true;
        } else {
            this.wrong++;
            this.total++;
            return false;
        }
    }



    public WordPair getCurrentWordPair() {
        return currentWordPair;
    }

    public void setCurrentWordPair(WordPair currentWordPair) {
        this.currentWordPair = currentWordPair;
    }

}
