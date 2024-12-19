/*
Program Name: Player.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Custom class extending DynamicShape with
velocity and logic to control flapping and audio
 */

package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.page.GamePage;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.DynamicShape;

import java.util.Random;

public class Player extends DynamicShape implements Constants {
    // Private variables for Player
    private double yVelocity;
    private AnimationTimer timer;
    private int currentImg;

    // Constructor to set default position and image when ready
    public Player(int width, int height) {
        super(width, height);
        super.setPosition(280,100);
        this.currentImg = 0;
        this.setImage(0);
    }

    // Method to set Player Y Velocity
    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Method to return current Player Y velocity
    public double getYVelocity() {
        return this.yVelocity;
    }

    // Method to return current image as index
    public int getCurrentImage() {
        return this.currentImg;
    }

    // Method to set image of the Player (rectangle)
    public void setImage(int index) {
        super.setFill(new ImagePattern(Main.sprites.get(index)));
    }

    // Method to return rolled image index
    public int getRolledImage() {
        return new Random().nextInt(0,5);
    }

    // Method to play audio for every score increment
    public void playScoreAudio() {
        Main.sfxPlayer.setMedia(new Media(
                Main.class.getResource("/org/dinne/gui_game/china.mp3").toExternalForm()
        ));
        Main.sfxPlayer.setVolume(1.5f);
        Main.sfxPlayer.play();
    }

    // Method to play audio when game is over
    private void playAudio() {
        Main.sfxPlayer.setMedia(new Media(
                Main.class.getResource("/org/dinne/gui_game/YoureFired.mp3").toExternalForm()
        ));
        Main.sfxPlayer.setVolume(1f);
        Main.sfxPlayer.play();
    }

    // Method implemented to draw the player every frame
    @Override
    public void draw() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Set Y velocity of the Player
                yVelocity += 0.1 * GRAVITY;
                if (yVelocity > 7) yVelocity = 7;

                double ry = getY();
                setY(ry + yVelocity);

                // If game is over stop adding to Y velocity
                if (!Main.gameRunning) {
                    timer.stop();
                    setY(getY());

                    for (PipeObject pipe : GamePage.pipes) {
                        pipe.stop();
                    }

                    playAudio();
                    GamePage.showUIComponents();

                    if (Main.currentScore > Main.highScore) {
                        Main.highScore = Main.currentScore;
                        GamePage.updateScores();
                    }
                }

                // If Player is out of window bounds game is over
                if (getY() > HEIGHT - 20 || getY() < -5) {
                    System.out.println("game over");
                    Main.gameRunning = false;
                }
            }
        };

        // Run timer
        timer.start();
    } // end of draw() method
} // end of Player class
