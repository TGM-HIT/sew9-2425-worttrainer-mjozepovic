package main;

import model.WordCoach;
import model.WordPair;
import persistenz.SessionSaverJson;
import persistenz.SessionSaver;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Test");


        WordCoach wc = new WordCoach();
        WordPair wp1 = new WordPair("Dog", "https://i.pinimg.com/originals/72/93/16/729316e27e33cd0e329cf99a16dd3ddb.jpg");
        WordPair wp2 = new WordPair("Cat", "https://i.pinimg.com/736x/b3/47/98/b34798360e8445729d99d4972eb91ece.jpg");

        SessionSaver sessionSaver = new SessionSaverJson();

        sessionSaver.save(wc);
    }


}
