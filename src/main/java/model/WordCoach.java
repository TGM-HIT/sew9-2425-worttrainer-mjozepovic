package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class WordCoach {

    private int right;
    private int total;
    private int currentWordPairIndex;

    ArrayList<WordPair> wordPairs;


    public WordCoach() {
        this.wordPairs = new ArrayList<>();
        this.currentWordPairIndex = -1;
    }

    public void chooseCurrentWordPair() {
        if (!wordPairs.isEmpty()) {
            this.currentWordPairIndex = ((int) (Math.random() * wordPairs.size()));
        }
    }

    public void chooseCurrentWordPair(int index) {
        if (index < 0 || index >= wordPairs.size()) {
            throw new IndexOutOfBoundsException("Angegebener Index liegt außerhalb der Grenzen, Index: " + index);
        } else {
            this.currentWordPairIndex = index;
        }
    }

    public boolean checkWordPair(String guessedWord) {

        if(this.wordPairs.get(currentWordPairIndex).getWord().equalsIgnoreCase(guessedWord)) {
            //this.wordPairs.remove(currentWordPairIndex);
            chooseCurrentWordPair();
            this.right++;
            this.total++;
            return true;
        } else {
            this.total++;
            return false;
        }
    }

    public void addWordPair(WordPair wordPair) {
        this.wordPairs.add(wordPair);
    }



    public void setWordPairs(ArrayList<WordPair> wordPairs) {
        this.wordPairs = wordPairs;
    }

    public ArrayList<WordPair> getWordPairs() {
        return wordPairs;
    }

    public int getCurrentWordPairIndex() {
        return currentWordPairIndex;
    }

    public void setCurrentWordPairIndex(int currentWordPairIndex) {
        this.currentWordPairIndex = currentWordPairIndex;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
