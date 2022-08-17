//318781630.
package game.levels;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.partipcans.AnimationRunner;
import game.partipcans.Counter;
import game.partipcans.GameLevel;
import game.partipcans.LevelInformation;
import game.partipcans.pause.GameOver;
import game.partipcans.pause.KeyPressStoppableAnimation;
import game.partipcans.pause.YouWin;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Itamar Bachar.
 */
public class GameFlow {
    private static int highScore;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private GUI gui;
    private Sleeper sleeper;
    private Counter score;

    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
        this.runner = new AnimationRunner(gui, sleeper);
        this.keyboard = gui.getKeyboardSensor();
        this.score = new Counter();
    }

    /**
     * the method run the game.
     *
     * @param levels the level that in the current game.
     */
    public void runLevels(List<LevelInformation> levels) throws Exception {
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            counter++;
            // create new level instance.
            GameLevel level = new GameLevel(levelInfo, gui, sleeper, keyboard, runner, score);
            level.initialize();
            // run while the user win or lose.
            do {
                level.run();
            } while (!level.shouldStop());
            // if the user lose print to thw screen,until he click space.
            if (level.getAmountOfBallsRemain() == 0) {
                while (!this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                    this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                            new GameOver(this.keyboard, this.score)), Color.GRAY);
                }
                this.writeScore();
                int oldScore = readFileAsString("highscores.txt");
                this.writeToFile("highscores.txt", oldScore);
                gui.close();
                break;
            }
        }
        // if the user win print to the screen until he press space.
        if (counter == levels.size()) {
            while (!this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                        new YouWin(this.keyboard, this.score)), Color.GRAY);
            }
            this.writeScore();
            int oldScore = readFileAsString("highscores.txt");
            this.writeToFile("highscores.txt",oldScore);
            gui.close();
        }

    }

    public void writeScore() {
        try {
            File myObj = new File("highscores.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String fileName, int score) {
        if (score < this.score.getValue())
            try {
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write("The highest score so far is: " + this.score.getValue());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

    public int readFileAsString(String fileName) throws Exception {
        String data = "";
        String score = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        Pattern r = Pattern.compile("\\d");
        Matcher m = r.matcher(data);
        while (m.find()) {
            String s = data.substring(m.start(), m.end());
            score = score.concat(s);
        }
        return Integer.parseInt(score);
    }

}
