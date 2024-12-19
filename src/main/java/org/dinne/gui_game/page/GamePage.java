/*
Program Name: GamePage.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Class where all game logic happens,
player and pipes running every frame and collision
logic implemented
 */

package org.dinne.gui_game.page;

import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.object.PipeObject;
import org.dinne.gui_game.object.Player;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.Listener;

import java.util.ArrayList;
import java.util.Random;

public class GamePage extends Scene implements Constants, Listener {
    // Static variables for the game
    public static Player player = null;
    public static ArrayList<PipeObject> pipes;

    // Private variables for class
    private final Random random = new Random();
    private final Pane parent;
    private final Image image = IMG_BG;

    // UI and pause delay for UI and game logic
    private static Text gameOverText, scoreText, highScoreText;
    private static Button restart, menu;
    private static PauseTransition pause;

    // Constructor
    public GamePage(Parent parent, double w, double h) {
        super(parent, w, h);
        Main.gameRunning = true;
        Main.currentScore = 0;
        this.parent = (Pane) parent;

        // Set variables
        pipes = new ArrayList<>();
        pipes.clear();
        player = new Player(35,35);

        restart = new Button("Restart");
        menu = new Button("Main Menu");
        gameOverText = new Text("Game Over!");
        scoreText = new Text("Score: " + Main.currentScore);
        highScoreText = new Text("High Score: " + Main.highScore);

        pause = new PauseTransition(Duration.seconds(2.27));

        // Create Player
        if (!(player == null)) {
            player.setYVelocity(0.1);
            player.draw();

            this.parent.getChildren().add(player);
        }

        // Run necessary methods for the scene
        this.interact();
        this.drawPipes();
        this.setBackground();
        this.setUIComponents();
        this.setScoreComponents();
    } // end of GamePage() Constructor

    // Private method to restart game on game over
    private void restartGame() {
        // Set variables like constructor
        Main.gameRunning = true;
        Main.currentScore = 0;
        this.parent.getChildren().clear();

        pipes = new ArrayList<>();
        player = new Player(35,35);

        restart = new Button("Restart");
        menu = new Button("Main Menu");
        gameOverText = new Text("Game Over!");
        scoreText = new Text("Score: " + Main.currentScore);
        highScoreText = new Text("High Score: " + Main.highScore);

        // Reset pause for pipes
        if (pause != null) {
            pause.stop();
        }
        pause = new PauseTransition(Duration.seconds(2.27));

        // Create Player
        if (!(player == null)) {
            player.setYVelocity(0.1);
            player.draw();

            this.parent.getChildren().add(player);
        }

        // Run necessary methods for the scene
        this.interact();
        this.drawPipes();
        this.setBackground();
        this.setUIComponents();
        this.setScoreComponents();
    } // end of restartGame() method

    // Method to set background image of scene
    private void setBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.DEFAULT.getWidth(),
                        BackgroundSize.DEFAULT.getHeight(),true,true,true,true)
        );

        parent.setBackground(new Background(backgroundImage));
    } // end of setBackground() method

    // Method to return instantiated PipeObject
    private PipeObject createPipe() {
        PipeObject pipe = new PipeObject(45);
        pipe.setXVelocity(1.2);
        pipe.setXPos(WIDTH);
        pipe.setYPos(random.nextInt(PIPE_MIN, PIPE_MAX + 1));

        return pipe;
    }

    // Method to spawn maximum of 4 pipes between set interval
    private void drawPipes() {
        if (Main.gameRunning) {
            pause.play();

            // Spawn pipe once pause delay is complete and repeat conditionally
            pause.setOnFinished(event -> {
                pause.stop();

                PipeObject pipe = createPipe();
                pipe.draw();
                pipes.add(pipe);
                parent.getChildren().add(pipe.getHolder());

                if (pipes.size() < 4) {
                    pause.play();
                }
            });
        }
    } // end of drawPipes() method

    // Method to set details of UI components for score
    private void setScoreComponents() {
        scoreText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        scoreText.setStroke(Color.BLACK);
        scoreText.setStrokeWidth(0.6);
        scoreText.setFill(Color.WHITE);
        scoreText.setX(5);
        scoreText.setY(HEIGHT - 50);
        this.parent.getChildren().add(scoreText);

        highScoreText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        highScoreText.setStroke(Color.BLACK);
        highScoreText.setStrokeWidth(0.6);
        highScoreText.setFill(Color.WHITE);
        highScoreText.setX(5);
        highScoreText.setY(HEIGHT - 15);
        this.parent.getChildren().add(highScoreText);

        scoreText.setVisible(true);
        scoreText.toFront();
        highScoreText.setVisible(true);
        highScoreText.toFront();
    } // end of setScoreComponents() method

    // Static method to update scores
    public static void updateScores() {
        scoreText.setText("Score: " + Main.currentScore);
        scoreText.toFront();
        highScoreText.setText("High Score: " + Main.highScore);
        highScoreText.toFront();
    }

    // Method to set details of UI components for the game over
    private void setUIComponents() {
        gameOverText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 80));
        gameOverText.setStroke(Color.BLACK);
        gameOverText.setStrokeWidth(2);
        gameOverText.setFill(Color.RED);
        gameOverText.setX(72);
        gameOverText.setY(100);
        this.parent.getChildren().add(gameOverText);

        restart.setPrefSize(200,90);
        restart.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        restart.setFocusTraversable(false);
        restart.setTextFill(Color.WHITE);
        restart.setBackground(Background.fill(new Color(0,0,0,0.5)));
        restart.setTranslateX(190);
        restart.setTranslateY(200);
        this.parent.getChildren().add(restart);

        menu.setPrefSize(180,50);
        menu.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        menu.setFocusTraversable(false);
        menu.setTextFill(Color.WHITE);
        menu.setBackground(Background.fill(new Color(0,0,0,0.5)));
        menu.setTranslateX(200);
        menu.setTranslateY(300);
        this.parent.getChildren().add(menu);

        gameOverText.setVisible(false);
        restart.setVisible(false);
        menu.setVisible(false);
    } // end of setUIComponents() method

    // Method to be called to show UI components on game over
    public static void showUIComponents() {
        gameOverText.setVisible(true);
        gameOverText.toFront();
        restart.setVisible(true);
        restart.toFront();
        menu.setVisible(true);
        menu.toFront();
    }

    // Method to roll a random image for Player that is at least distinct
    private void rollAndSetImageIfPossible() {
        int newImage = player.getRolledImage();

        if (newImage == player.getCurrentImage()) {
            rollAndSetImageIfPossible();
        } else {
            player.setImage(newImage);
        }
    }

    // Method implemented to handle all button events
    @Override
    public void interact() {
        // "Flap" the Player every time mouse is clicked
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY && Main.gameRunning) {
                rollAndSetImageIfPossible();
                player.setYVelocity(-2.7);
            }
        });

        // Restart the GamePage scene
        restart.setOnMouseClicked(event -> {
            restartGame();
        });

        restart.setOnMouseEntered(event -> {
            restart.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        });

        restart.setOnMouseExited(event -> {
            restart.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        });

        // Go back to the TitlePage
        menu.setOnMouseClicked(event -> {
            Main.restartStage();
        });

        menu.setOnMouseEntered(event -> {
            menu.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 24));
        });

        menu.setOnMouseExited(event -> {
            menu.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        });
    } // end of interact() method
} // end of GamePage class
