import java.net.MalformedURLException;
import java.net.URL;

public class WordPair {

    private String word;
    private String image;


    public WordPair(String word, String image) throws MalformedURLException {

        if (isValidImage(image)) {
            this.word = word;
            this.image = image;
        } else {
            throw new MalformedURLException("Ungültige URL");
        }

    }


    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public boolean isValidImage(String image) {
        return true;
    }


    public String getImage() {
        return this.image;
    }

    public void setImage(String image) throws MalformedURLException {

        if (isValidImage(image)) {
            this.image = image;
        } else {
            throw new MalformedURLException("Ungültige URL");
        }



    }
}