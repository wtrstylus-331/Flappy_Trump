package org.dinne.gui_game;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application implements Constants {
    // Variables
    final int width = 600;
    final int height = 400;

    private Random random = new Random();
    Player plr = new Player(40,40);
    PipeObject pipeTest = new PipeObject(50);
    ArrayList<Image> sprites = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        initializeSprites();

        pipeTest.setXPos(250);
        pipeTest.setYPos(0);
        pipeTest.setXVelocity(2);
        pipeTest.draw();

        plr.setImage(IMG_1);
        plr.setYVelocity(0.1);
        plr.draw();

        pane.getChildren().add(plr);
        pane.getChildren().add(pipeTest.getHolder());

        /*
        AnimationTimer movement = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!flapped) {
                    yVelocity += 0.1 * GRAVITY;
                    if (yVelocity > 7) yVelocity = 7;
                }

                double ry = character.getY();
                character.setY(ry + yVelocity);

                //character.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            }
        }; */

        //movement.start();

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                int choice = random.nextInt(0,3);
                plr.setImage(sprites.get(choice));
                plr.setYVelocity(-2.7);
            }
        });

        primaryStage.setTitle("Flappy Trump");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeSprites() {
        for (int i = 0; i < 3; i++) {
            sprites.add(new Image(getClass().getResourceAsStream("image" + (i + 1) + ".png")));
        }
    }

    private boolean isCollision(double x, double y, Rectangle other) {
        double otherX = other.getX();
        double otherY = other.getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        return x < otherX + otherWidth && x + plr.getWidth() > otherX
                && y < otherY + otherHeight && y + plr.getHeight() > otherY;
    }
}