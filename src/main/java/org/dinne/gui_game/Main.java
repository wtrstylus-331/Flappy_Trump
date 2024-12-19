/*
Program Name: Main.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Runs the main application stage (window)
 */

package org.dinne.gui_game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.dinne.gui_game.page.TitlePage;
import org.dinne.gui_game.util.AudioPlayer;
import org.dinne.gui_game.util.Constants;

import java.util.ArrayList;

public class Main extends Application implements Constants {

    // Scores variables
    public static int currentScore, highScore;

    // Utility variables
    public static ArrayList<Image> sprites = new ArrayList<>();
    public static Stage stage;
    public static boolean gameRunning = false;
    public static AudioPlayer bgMusic = new AudioPlayer(new Media(
            Main.class.getResource("/org/dinne/gui_game/BackgroundMusic.mp3").toExternalForm()
    ));
    public static AudioPlayer sfxPlayer = new AudioPlayer(null);

    // Method to show TitlePage on the stage
    public static void restartStage() {
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    // Method to create stage and show
    @Override
    public void start(Stage primaryStage) {
        // Create TitlePage scene
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, WIDTH, HEIGHT);
        initializeSprites();

        // Reset scores and play audio
        currentScore = highScore = 0;
        bgMusic.play();
        bgMusic.setCycle(MediaPlayer.INDEFINITE);
        bgMusic.setVolume(0.5f);

        // Set stage details and display
        stage = primaryStage;
        primaryStage.setTitle("Flappy Trump");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image3.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Run the program
    public static void main(String[] args) {
        launch();
    }

    // Method to get images from resource directory and add to static ArrayList for package-wide access
    private void initializeSprites() {
        for (int i = 0; i < 5; i++) {
            sprites.add(new Image(getClass().getResourceAsStream("image" + (i + 1) + ".png")));
        }
    }
} // end of Main class