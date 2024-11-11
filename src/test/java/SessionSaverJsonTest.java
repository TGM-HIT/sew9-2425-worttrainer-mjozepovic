import model.WordCoach;
import model.WordPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistenz.SessionSaverJson;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionSaverJsonTest {

    private WordCoach wordCoach;
    private SessionSaverJson sessionSaver;

    @BeforeEach
    public void setUp() {
        wordCoach = new WordCoach();
        sessionSaver = new SessionSaverJson();
    }

    @Test
    public void testSaveAndLoad() throws IOException, ClassNotFoundException, URISyntaxException {
        wordCoach.addWordPair(new WordPair("Dog", "https://example.com/dog.jpg"));
        wordCoach.addWordPair(new WordPair("Cat", "https://example.com/cat.jpg"));

        sessionSaver.save(wordCoach);
        WordCoach loadedCoach = sessionSaver.load();

        assertEquals(wordCoach.getWordPairs().size(), loadedCoach.getWordPairs().size());
        assertEquals(wordCoach.getWordPairs().get(0).getWord(), loadedCoach.getWordPairs().get(0).getWord());
    }
}