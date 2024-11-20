# sew9-2425-worttrainer-mjozepovic

## Overview

The **WordCoach Project** is a Java-based educational tool designed to improve vocabulary through interactive word-image pairing exercises. The program allows users to guess words based on displayed images, tracks their progress, and saves their session for later continuation.

---

## Features

- Interactive GUI with **JFrame** for user interaction.
- Tracks statistics such as total attempts and correct guesses.
- Saves and loads session data using **JSON** serialization via the Jackson library.
- Randomized word selection to keep the game engaging.
- Customizable list of word-image pairs.
- Error handling for invalid inputs and URLs.

---

## Project Structure

The project is structured as follows:

```
src/
├── main/
│   ├── java/
│   │   ├── main/
│   │   │   └── Main.java
│   │   ├── model/
│   │   │   ├── WordCoach.java
│   │   │   └── WordPair.java
│   │   ├── persistenz/
│   │   │   ├── SessionSaver.java
│   │   │   └── SessionSaverJson.java
│   │   └── view/
│   │       └── View.java
│   └── resources/
│       └── wordcoach.json
```

---

## Requirements

- Java 17 or later
- [Jackson Library](https://github.com/FasterXML/jackson)
- An IDE or build tool supporting Gradle or Maven

---

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-repository/wordcoach.git
   ```
2. Open the project in your IDE (e.g., IntelliJ IDEA, Eclipse).
3. Add the Jackson library to your project:
   - Gradle:
     ```gradle
     implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
     ```
   - Maven:
     ```xml
     <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.15.2</version>
     </dependency>
     ```
4. Run the `Main` class to start the application.

---

## How to Use

1. Launch the application by running `Main.java`.
2. Follow the on-screen instructions:
   - Enter your guess for the displayed word.
   - The app will notify you if your guess was correct or not.
3. View statistics (total attempts and correct guesses) at any time.
4. Stop the program to save your progress automatically.
5. Relaunch the app to resume from your last session.

---

## Classes

### `Main`
- Entry point of the application. Handles initial setup and session management.

### `WordCoach`
- Core logic for managing word pairs, tracking statistics, and random word selection.

### `WordPair`
- Represents a single word-image pair with validation for proper URLs.

### `SessionSaver`
- Interface for session saving and loading functionality.

### `SessionSaverJson`
- Implementation of `SessionSaver` using JSON for persistence.

### `View`
- Swing-based GUI for user interaction and displaying game data.

---

## Sample Word Pairs

The application includes the following preloaded word-image pairs:

| Word   | Image URL |
|--------|-----------|
| Dog    | ![Link](https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*) |
| Cat    | ![Link](https://www.wfla.com/wp-content/uploads/sites/71/2023/05/GettyImages-1389862392.jpg?strip=1) |
| Rat    | ![Link](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Rattus_norvegicus_-_Brown_rat_02.jpg/800px-Rattus_norvegicus_-_Brown_rat_02.jpg) |
| Mouse  | ![Link](https://smarterpestcontrol.com/wp-content/uploads/2017/08/Mouse-on-Wooden-Table.jpg) |
| Tiger  | ![Link](https://media.4-paws.org/5/4/4/c/544c2b2fd37541596134734c42bf77186f0df0ae/VIER%20PFOTEN_2017-10-20_164-3854x2667-1920x1329.jpg) |
| Hamster| ![Link](https://www.zooroyal.de/magazin/wp-content/uploads/2017/04/hamster-760x560.jpg) |

---

## Contributing

Contributions are welcome! Feel free to fork the project, make improvements, and submit a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details. 

---

## Acknowledgments

- [Jackson Library Documentation](https://github.com/FasterXML/jackson)
- Java Swing Tutorials for GUI components.
