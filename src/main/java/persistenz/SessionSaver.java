package persistenz;

import model.WordCoach;

import java.io.IOException;

public interface SessionSaver {
    public void save(WordCoach wt) throws IOException;
    public WordCoach load() throws IOException,ClassNotFoundException;
}