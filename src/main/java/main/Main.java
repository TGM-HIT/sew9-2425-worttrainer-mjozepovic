package main;

import model.WordCoach;
import model.WordPair;
import persistenz.SessionSaverJson;
import persistenz.SessionSaver;
import view.View;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Matteo Jozepovic
 * @version 2024-11-16
 * This is the Main class and the starting point of the application.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException {

        SessionSaver sessionSaver = new SessionSaverJson();
        WordCoach wc = loadWordCoach(sessionSaver);

        if (wc != null) {
            System.out.println("WordCoach was successfully loaded/created.");
        } else {
            System.out.println("Error while trying to load WordCoach.");
        }

        View wortTrainerView = new View(wc, sessionSaver);

        wortTrainerView.start();
    }

    private static WordCoach loadWordCoach(SessionSaver sessionSaver) throws URISyntaxException {
        try{
            WordCoach wc = sessionSaver.load();

            if(wc == null) {
                System.out.println("No previous data found, new WordCoach will be created.");
                return createWordCoach();
            }
            return wc;
        } catch(IOException | ClassNotFoundException | URISyntaxException e) {
            System.out.println("Error while trying to load WordCoach, creating a new one instead.");
            return createWordCoach();
        }
    }

    private static WordCoach createWordCoach() throws URISyntaxException {
        WordCoach wc = new WordCoach();
        try {
            wc.addWordPair(new WordPair("Dog", "https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*"));
            wc.addWordPair(new WordPair("Cat", "https://www.wfla.com/wp-content/uploads/sites/71/2023/05/GettyImages-1389862392.jpg?strip=1"));
            wc.addWordPair(new WordPair("Rat", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Rattus_norvegicus_-_Brown_rat_02.jpg/800px-Rattus_norvegicus_-_Brown_rat_02.jpg"));
            wc.addWordPair(new WordPair("Mouse", "https://smarterpestcontrol.com/wp-content/uploads/2017/08/Mouse-on-Wooden-Table.jpg"));
            wc.addWordPair(new WordPair("Tiger", "https://media.4-paws.org/5/4/4/c/544c2b2fd37541596134734c42bf77186f0df0ae/VIER%20PFOTEN_2017-10-20_164-3854x2667-1920x1329.jpg"));
            wc.addWordPair(new WordPair("Hamster", "https://www.zooroyal.de/magazin/wp-content/uploads/2017/04/hamster-760x560.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wc;
    }
}
