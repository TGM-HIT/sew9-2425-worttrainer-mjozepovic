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
 * Diese Klasse `Main` ist der Einstiegspunkt der Anwendung.
 * Sie initialisiert die notwendigen Objekte und startet den Ablauf.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException {

        // Erstellt ein SessionSaver-Objekt, das für das Speichern und Laden des Programmstatus verantwortlich ist.
        SessionSaver sessionSaver = new SessionSaverJson();

        // Lädt den aktuellen Zustand des WordCoach-Objekts oder erstellt ein neues, falls keine vorherigen Daten existieren.
        WordCoach wc = loadWordCoach(sessionSaver);

        // Gibt Feedback über den Erfolg des Ladens oder die Notwendigkeit, ein neues Objekt zu erstellen.
        if (wc != null) {
            System.out.println("WordCoach wurde erfolgreich geladen/erstellt.");
        } else {
            System.out.println("Fehler beim Laden von WordCoach.");
        }

        // Erstellt die View, die für die Benutzerinteraktion zuständig ist, und übergibt das geladene WordCoach-Objekt.
        View wortTrainerView = new View(wc, sessionSaver);

        // Startet die Benutzeroberfläche des Programms.
        wortTrainerView.start();
    }

    /**
     * Versucht, ein WordCoach-Objekt aus einer gespeicherten Sitzung zu laden.
     * Falls ein Fehler auftritt oder keine Daten existieren, wird ein neues Objekt erstellt.
     *
     * @param sessionSaver Das SessionSaver-Objekt, das für das Laden verantwortlich ist.
     * @return Ein geladenes oder neu erstelltes WordCoach-Objekt.
     * @throws URISyntaxException Wenn ein Problem mit der URI der Ressource auftritt.
     */
    private static WordCoach loadWordCoach(SessionSaver sessionSaver) throws URISyntaxException {
        try {
            // Versucht, den WordCoach-Zustand aus einer Datei zu laden.
            WordCoach wc = sessionSaver.load();

            // Falls keine gespeicherten Daten gefunden werden, wird ein neues Objekt erstellt.
            if (wc == null) {
                System.out.println("Keine vorherigen Daten gefunden, ein neuer WordCoach wird erstellt.");
                return createWordCoach();
            }
            return wc;
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            // Falls ein Fehler beim Laden auftritt, wird ein neues WordCoach-Objekt erstellt.
            System.out.println("Fehler beim Laden von WordCoach, ein neuer wird erstellt.");
            return createWordCoach();
        }
    }

    /**
     * Erstellt ein neues WordCoach-Objekt und fügt ihm Standard-Wortpaare hinzu.
     *
     * @return Ein neues WordCoach-Objekt mit initialen Daten.
     * @throws URISyntaxException Wenn ein Problem mit der URI der Bildressourcen auftritt.
     */
    private static WordCoach createWordCoach() throws URISyntaxException {
        // Erstellt eine neue Instanz von WordCoach.
        WordCoach wc = new WordCoach();
        try {
            // Fügt vordefinierte Wortpaare mit zugehörigen Bild-URLs hinzu.
            wc.addWordPair(new WordPair("Dog", "https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*"));
            wc.addWordPair(new WordPair("Cat", "https://www.wfla.com/wp-content/uploads/sites/71/2023/05/GettyImages-1389862392.jpg?strip=1"));
            wc.addWordPair(new WordPair("Rat", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Rattus_norvegicus_-_Brown_rat_02.jpg/800px-Rattus_norvegicus_-_Brown_rat_02.jpg"));
            wc.addWordPair(new WordPair("Mouse", "https://smarterpestcontrol.com/wp-content/uploads/2017/08/Mouse-on-Wooden-Table.jpg"));
            wc.addWordPair(new WordPair("Tiger", "https://media.4-paws.org/5/4/4/c/544c2b2fd37541596134734c42bf77186f0df0ae/VIER%20PFOTEN_2017-10-20_164-3854x2667-1920x1329.jpg"));
            wc.addWordPair(new WordPair("Hamster", "https://www.zooroyal.de/magazin/wp-content/uploads/2017/04/hamster-760x560.jpg"));
        } catch (Exception e) {
            // Falls ein Fehler beim Hinzufügen der Wortpaare auftritt, wird der Fehler ausgegeben.
            e.printStackTrace();
        }
        return wc;
    }
}
