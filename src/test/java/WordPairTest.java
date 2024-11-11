import model.WordPair;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordPairTest {

    @Test
    public void testValidImageUrl() throws MalformedURLException, URISyntaxException {
        WordPair wp = new WordPair("Dog", "https://example.com/dog.jpg");
        assertEquals("Dog", wp.getWord());
        assertEquals("https://example.com/dog.jpg", wp.getImage());
    }

    @Test
    public void testInvalidImageUrl() {
        assertThrows(URISyntaxException.class, () -> {
            new WordPair("Dog", "invalid_url");
        });
    }
}