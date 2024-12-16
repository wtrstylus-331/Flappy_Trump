package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.ImagePattern;
import org.dinne.gui_game.Main;
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
        this.currentImg = 1;
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
                }

                if (getY() > HEIGHT - 20 || getY() < 0) {
                    System.out.println("game over");
                    Main.gameRunning = false;
                }
            }
        };

        timer.start();
    }
}
