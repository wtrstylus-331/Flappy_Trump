package org.dinne.gui_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application implements Constants {
    // Variables
    final int width = 600;
    final int height = 400;

    public static Player plr = new Player(40,40);
    PipeObject pipeTest = new PipeObject(50);
    public static ArrayList<Image> sprites = new ArrayList<>();
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        TitlePage scene = new TitlePage(pane, width, height);
        initializeSprites();

        //pipeTest.setXPos(250);
        //pipeTest.setYPos(0);
        //pipeTest.setXVelocity(2);
        //pipeTest.draw();

        //plr.setYVelocity(0.1);
        //plr.draw();

        //pane.getChildren().add(plr);
        //pane.getChildren().add(pipeTest.getHolder());
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