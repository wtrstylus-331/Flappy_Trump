package org.dinne.gui_game;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application implements Constants {
    // Variables
    final int width = 600;
    final int height = 400;

    private double yVelocity = 0.0;
    private boolean flapped = false;

    private Random r = new Random();
    Rectangle character = null;
    Player plr = new Player(40,20);
    PipeObject pipe = new PipeObject(50);

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);

        character = new Rectangle(50, 50);
        character.setFill(new ImagePattern(IMG_1));
        character.setX(250);
        character.setY(100);

        Rectangle one = new Rectangle(50, 50);
        one.setFill(Color.BLACK);
        one.setX(50);
        one.setY(100);

        Rectangle two = new Rectangle(50, 50);
        two.setFill(Color.BLACK);
        two.setX(300);
        two.setY(75);

        pipe.setX(200);
        pipe.setY(200);

        plr.setImage(IMG_1);
        plr.setYVelocity(0.1);
        plr.draw();

        pane.getChildren().add(character);
        pane.getChildren().add(one);
        pane.getChildren().add(two);
        pane.getChildren().add(plr);
        pane.getChildren().add(pipe);

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
        };

        //movement.start();

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!flapped) {
                    flapped = true;

//                    int random = r.nextInt(120,255);
//                    character.setFill(Color.rgb(random, random, 0));

                    System.out.println("flapped");
                    yVelocity = -2.5;

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.25));
                    pause.setOnFinished(e -> {
                        flapped = false;
                    });
                    pause.play();
                }
            }
        });

        primaryStage.setTitle("Flappy Trump");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private boolean isCollision(double x, double y, Rectangle other) {
        double otherX = other.getX();
        double otherY = other.getY();
        double otherWidth = other.getWidth();
        double otherHeight = other.getHeight();

        return x < otherX + otherWidth && x + character.getWidth() > otherX
                && y < otherY + otherHeight && y + character.getHeight() > otherY;
    }
}