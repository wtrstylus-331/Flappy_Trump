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
    private double yVelocity;
    private AnimationTimer timer;
    private int currentImg;

    public Player(int width, int height) {
        super(width, height);
        super.setPosition(280,100);
        this.currentImg = 0;
        this.setImage(0);
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public int getCurrentImage() {
        return this.currentImg;
    }

    public void setImage(int index) {
        super.setFill(new ImagePattern(Main.sprites.get(index)));
    }

    public int getRolledImage() {
        return new Random().nextInt(0,5);
    }

    public void playScoreAudio() {
        Main.sfxPlayer.setMedia(new Media(
                Main.class.getResource("/org/dinne/gui_game/china.mp3").toExternalForm()
        ));
        Main.sfxPlayer.play();
    }

    private void playAudio() {
        Main.sfxPlayer.setMedia(new Media(
                Main.class.getResource("/org/dinne/gui_game/YoureFired.mp3").toExternalForm()
        ));
        Main.sfxPlayer.play();
    }

    @Override
    public void draw() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                yVelocity += 0.1 * GRAVITY;
                if (yVelocity > 7) yVelocity = 7;

                double ry = getY();
                setY(ry + yVelocity);

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

                if (getY() > HEIGHT - 20 || getY() < -5) {
                    System.out.println("game over");
                    Main.gameRunning = false;
                }
            }
        };

        timer.start();
    }
}
