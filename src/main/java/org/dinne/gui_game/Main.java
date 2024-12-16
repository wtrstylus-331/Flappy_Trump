package org.dinne.gui_game;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.dinne.gui_game.page.TitlePage;
import org.dinne.gui_game.util.Constants;

import java.util.ArrayList;

public class Main extends Application implements Constants {
    // Variables
    final int width = 600;
    final int height = 400;

    public static ArrayList<Image> sprites = new ArrayList<>();
    public static Stage stage;
    public static boolean gameRunning = false;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, width, height);
        initializeSprites();

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