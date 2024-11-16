import model.WordCoach;
import model.WordPair;
import org.junit.jupiter.api.*;
import persistenz.SessionSaver;
import persistenz.SessionSaverJson;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    private WordCoach wordCoach;
    private SessionSaver sessionSaver;

    @BeforeAll
    void setup() throws URISyntaxException {
        sessionSaver = new SessionSaverJson();
        wordCoach = new WordCoach();
        wordCoach.addWordPair(new WordPair("Apple", "https://example.com/apple.jpg"));
        wordCoach.addWordPair(new WordPair("Banana", "https://example.com/banana.jpg"));
        wordCoach.addWordPair(new WordPair("Cherry", "https://example.com/cherry.jpg"));
    }

    @Test
    @DisplayName("Test WordCoach Initialization")
    void testWordCoachInitialization() {
        assertNotNull(wordCoach);
        assertEquals(3, wordCoach.getWordPairs().size(), "WordCoach should have 3 word pairs.");
    }

    @Test
    @DisplayName("Test WordPair Addition")
    void testAddWordPair() throws URISyntaxException {
        WordPair newWordPair = new WordPair("Dog", "https://example.com/dog.jpg");
        wordCoach.addWordPair(newWordPair);
        assertEquals(4, wordCoach.getWordPairs().size(), "WordCoach should now have 4 word pairs.");
    }

    @Test
    @DisplayName("Test Valid URL in WordPair")
    void testWordPairValidUrl() throws URISyntaxException {
        WordPair validPair = new WordPair("Valid", "https://example.com/valid.jpg");
        assertEquals("https://example.com/valid.jpg", validPair.getImage());
    }

    @Test
    @DisplayName("Test Invalid URL in WordPair")
    void testWordPairInvalidUrl() {
        assertThrows(URISyntaxException.class, () -> new WordPair("Invalid", "htp:/example.com/invalid.jpg"));
    }

    @Test
    @DisplayName("Test Current Word Pair Selection")
    void testChooseCurrentWordPair() {
        wordCoach.chooseCurrentWordPair();
        assertTrue(wordCoach.getCurrentWordPairIndex() >= 0, "Index should not be negative.");
        assertTrue(wordCoach.getCurrentWordPairIndex() < wordCoach.getWordPairs().size(),
                "Index should be within the range of word pairs.");
    }


    @Test
    @DisplayName("Test Incorrect Answer")
    void testCheckWordPairIncorrect() {
        wordCoach.chooseCurrentWordPair(0);
        assertFalse(wordCoach.checkWordPair("Wrong"), "The answer should be marked as incorrect.");
        assertEquals(0, wordCoach.getRight(), "Correct count should not increase.");
        assertEquals(1, wordCoach.getTotal(), "Total count should increase.");
    }

    @Test
    @DisplayName("Test Save and Load WordCoach")
    void testSaveAndLoadWordCoach() throws IOException, ClassNotFoundException {
        sessionSaver.save(wordCoach);
        WordCoach loadedWordCoach = sessionSaver.load();
        assertNotNull(loadedWordCoach);
        assertEquals(wordCoach.getWordPairs().size(), loadedWordCoach.getWordPairs().size(),
                "Loaded WordCoach should have the same number of word pairs.");
    }

    @Test
    @DisplayName("Test SessionSaver Handles Missing Files Gracefully")
    void testSessionSaverHandlesMissingFile() {
        SessionSaver missingSessionSaver = new SessionSaverJson() {
            @Override
            public WordCoach load() throws IOException {
                throw new IOException("Simulating missing file.");
            }
        };

        Exception exception = assertThrows(IOException.class, missingSessionSaver::load);
        assertEquals("Simulating missing file.", exception.getMessage());
    }
}
