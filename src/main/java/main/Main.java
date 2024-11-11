package main;

import model.WordCoach;
import model.WordPair;
import persistenz.SessionSaverJson;
import persistenz.SessionSaver;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException {

        System.out.println("Test");

        WordCoach wc = new WordCoach();
        WordPair wp1 = new WordPair("Dog", "https://i.pinimg.com/originals/72/93/16/729316e27e33cd0e329cf99a16dd3ddb.jpg");
        WordPair wp2 = new WordPair("Cat", "https://i.pinimg.com/736x/b3/47/98/b34798360e8445729d99d4972eb91ece.jpg");
        wc.addWordPair(wp1);
        wc.addWordPair(wp2);

        SessionSaver sessionSaver = new SessionSaverJson();

        sessionSaver.save(wc);
        System.out.println(sessionSaver.load().getWordPairs().get(1).getWord());
    }


}
