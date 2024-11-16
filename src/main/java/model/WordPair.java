package model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Matteo Jozepovic
 * @version 2024-11-16
 * This class represents a WordPair in the WordCoach application.
 */
public class WordPair {

    private String word;
    private String image;


    public WordPair() {
    }

    public WordPair(String word, String image) throws URISyntaxException {
        setWord(word);
        setImage(image);
    }



    public String getImage() {
        return this.image;
    }

    public void setImage(String image) throws URISyntaxException {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image cannot be empty.");
        }

        try {
            URI uri = new URI(image);
            if (!uri.isAbsolute() || (!uri.getScheme().equals("http") && !uri.getScheme().equals("https"))) {
                throw new URISyntaxException(image, "URL must start with http or https.");
            }
            this.image = image;
        } catch (URISyntaxException e) {
            throw new URISyntaxException("Invalid URL: " + image, e.getMessage());
        }
    }


    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty.");
        }
        this.word = word;
    }
}