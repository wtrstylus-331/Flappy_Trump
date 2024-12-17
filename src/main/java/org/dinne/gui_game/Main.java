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
    // Variables
    private final static int width = 600;
    private final static int height = 400;

    public static int currentScore, highScore;

    public static ArrayList<Image> sprites = new ArrayList<>();
    public static Stage stage;
    public static boolean gameRunning = false;
    public static AudioPlayer bgMusic = new AudioPlayer(new Media(
            Main.class.getResource("/org/dinne/gui_game/BackgroundMusic.mp3").toExternalForm()
    ));
    public static AudioPlayer sfxPlayer = new AudioPlayer(null);

    public static void restartStage() {
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, width, height);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, width, height);
        initializeSprites();

        currentScore = highScore = 0;

        bgMusic.play();
        bgMusic.setCycle(MediaPlayer.INDEFINITE);
        bgMusic.setVolume(0.75f);

        stage = primaryStage;
        primaryStage.setTitle("Flappy Trump");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image3.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeSprites() {
        for (int i = 0; i < 5; i++) {
            sprites.add(new Image(getClass().getResourceAsStream("image" + (i + 1) + ".png")));
        }
    }
}