package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.DynamicShape;

public class Player extends DynamicShape implements Constants {
    private double yVelocity;
    private AnimationTimer timer;
    private Image currentImg;

    public Player(int width, int height) {
        super(width, height);
        super.setPosition(100,100);
        currentImg = IMG_1;
        this.setImage(IMG_1);
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public Image getCurrentImage() {
        return currentImg;
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

                double ry = getY();
                setY(ry + yVelocity);

                if (getY() > HEIGHT || getY() < 0) {
                    System.out.println("game over");
                    timer.stop();
                }
            }
        };

        timer.start();
    }
}
