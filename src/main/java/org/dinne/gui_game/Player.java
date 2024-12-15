package org.dinne.gui_game;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;

public class Player extends DynamicShape implements Constants {
    private double yVelocity;
    private AnimationTimer timer;

    public Player(int width, int height) {
        super(width, height);
        super.setPosition(100,100);
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setImage(Image image) {
        super.setFill(new ImagePattern(image));
    }

    @Override
    public void draw() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                yVelocity += 0.1 * GRAVITY;
                if (yVelocity > 7) yVelocity = 7;

                double ry = getTranslateY();
                setTranslateY(ry + yVelocity);
            }
        };

        timer.start();
    }
}
