package persistenz;

import model.WordCoach;

import java.io.IOException;

/**
 * @author Matteo Jozepovic
 * @version 2024-11-16
 * This class is the interface for the saving and loading in the WordCoach application.
 */
public interface SessionSaver {
    public void save(WordCoach wt) throws IOException;
    public WordCoach load() throws IOException,ClassNotFoundException;
}