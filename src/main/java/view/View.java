package view;

import model.WordCoach;
import persistenz.SessionSaver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Matteo Jozepovic
 * @version 2024-11-16
 * This class represents the user interface for interacting with the WordCoach application.
 */
public class View {

    private WordCoach wordCoach;
    private SessionSaver sessionSaver;
    private JFrame frame;
    private JPanel panel;
    private JLabel statsLabel;
    private JLabel imageLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JButton stopButton;

    private int i = -1; // Used to determine the index of the current word pair

    public View(WordCoach wordCoach, SessionSaver sessionSaver) {
        this.wordCoach = wordCoach;
        this.sessionSaver = sessionSaver;

        // Initialize JFrame
        frame = new JFrame("WordCoach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));

        // Initialize main panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Statistics label
        statsLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(statsLabel, BorderLayout.NORTH);

        // Image label
        imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        // Input field and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout()); // Use BorderLayout for better organization

        inputField = new JTextField(20);

        submitButton = new JButton("Check");
        submitButton.addActionListener(e -> handleAnswer());

        stopButton = new JButton("Stop program");
        stopButton.addActionListener(e -> {
            showStatistics();
            saveWordCoach();
            System.exit(0);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(stopButton);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.PAGE_END);

        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Starts the WordCoach session.
     */
    public void start() {
        showNextWord();
    }

    /**
     * Displays the next word and its associated image.
     */
    private void showNextWord() {
        if (i > 0) {
            wordCoach.chooseCurrentWordPair();
        }
        i = 1;

        showImageAndStats();
        inputField.setText(""); // Clear the input field for the next word
    }

    /**
     * Handles the user's answer submission.
     */
    private void handleAnswer() {
        String userAnswer = inputField.getText();
        if (!userAnswer.isEmpty()) {
            checkAnswer(userAnswer);
            inputField.setText(""); // Clear the input field
            showNextWord(); // Proceed to the next word
        }
    }

    /**
     * Validates the user's answer and updates statistics.
     *
     * @param userAnswer The answer provided by the user.
     */
    private void checkAnswer(String userAnswer) {
        String correctWord = wordCoach.getWord(); // Retrieve the correct word
        boolean isCorrect = userAnswer.equalsIgnoreCase(correctWord);
        String message = isCorrect
                ? "Correct!"
                : "Incorrect! The correct word was: " + correctWord;
        JOptionPane.showMessageDialog(frame, message, "Result", JOptionPane.INFORMATION_MESSAGE);

        if (isCorrect) {
            wordCoach.setRight(wordCoach.getRight() + 1);
        }
        wordCoach.setTotal(wordCoach.getTotal() + 1);
    }

    /**
     * Displays the current word's image and statistics.
     */
    private void showImageAndStats() {
        String stats = "Total: " + wordCoach.getTotal() + ", Correct: " + wordCoach.getRight();
        statsLabel.setText(stats);

        String imageUrl = wordCoach.getImage(); // Retrieve the image URL
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(frame, stats + "\nError loading image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the user's statistics before exiting.
     */
    private void showStatistics() {
        String stats = "Total: " + wordCoach.getTotal() + ", Correct: " + wordCoach.getRight();
        JOptionPane.showMessageDialog(frame, stats, "Your Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Saves the current state of the WordCoach session.
     */
    private void saveWordCoach() {
        try {
            sessionSaver.save(wordCoach);
            System.out.println("WordCoach saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving WordCoach.");
        }
    }
}
