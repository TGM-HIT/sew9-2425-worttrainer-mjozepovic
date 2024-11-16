package persistenz;

import model.WordCoach;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * @author Matteo Jozepovic
 * @version 2024-11-16
 * This class makes the saving and loading of data in the WordCoach application possible.
 */
public class SessionSaverJson implements SessionSaver {
    private static final String FILE_PATH = "src/main/resources/wordcoach.json";
    private ObjectMapper objectMapper;

    public SessionSaverJson() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(WordCoach wordCoach) throws IOException {

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), wordCoach);
        System.out.println("WordCoach was saved as JSON.");
    }

    @Override
    public WordCoach load() throws IOException, ClassNotFoundException {

        WordCoach wordCoach = objectMapper.readValue(new File(FILE_PATH), WordCoach.class);
        System.out.println("WordCoach was successfully loaded from the JSON.");
        return wordCoach;
    }
}