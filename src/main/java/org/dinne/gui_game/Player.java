package org.dinne.gui_game;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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

    @Override
    public void setImage(Image image) {
        this.setFill(new ImagePattern(image));
    }

    @Override
    public void drawFrame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setX(getX() + yVelocity);
            }
        };

        timer.start();
    }
}
