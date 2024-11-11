import model.WordCoach;
import model.WordPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistenz.SessionSaverJson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class WordCoachTest {


    private WordCoach wordCoach;

    @BeforeEach
    public void setUp() {
        wordCoach = new WordCoach();
    }

    @Test
    public void testAddWordPair() throws MalformedURLException, URISyntaxException {
        WordPair wordPair = new WordPair("Dog", "https://example.com/dog.jpg");
        wordCoach.addWordPair(wordPair);
        assertEquals(1, wordCoach.getWordPairs().size());
        assertEquals("Dog", wordCoach.getWordPairs().get(0).getWord());
    }

    @Test
    public void testChooseCurrentWordPair() throws MalformedURLException, URISyntaxException {
        wordCoach.addWordPair(new WordPair("Dog", "https://example.com/dog.jpg"));
        wordCoach.addWordPair(new WordPair("Cat", "https://example.com/cat.jpg"));
        wordCoach.chooseCurrentWordPair();
        assertTrue(wordCoach.getCurrentWordPairIndex() >= 0);
    }

    @Test
    public void testCheckWordPairCorrect() throws MalformedURLException, URISyntaxException {
        WordPair wp = new WordPair("Dog", "https://example.com/dog.jpg");
        wordCoach.addWordPair(wp);
        wordCoach.chooseCurrentWordPair(0);
        assertTrue(wordCoach.checkWordPair("Dog"));
        assertEquals(1, wordCoach.getRight());
        assertEquals(1, wordCoach.getTotal());
    }

    @Test
    public void testCheckWordPairIncorrect() throws MalformedURLException, URISyntaxException {
        WordPair wp = new WordPair("Dog", "https://example.com/dog.jpg");
        wordCoach.addWordPair(wp);
        wordCoach.chooseCurrentWordPair(0);
        assertFalse(wordCoach.checkWordPair("Cat"));
        assertEquals(0, wordCoach.getRight());
        assertEquals(1, wordCoach.getTotal());
    }
}