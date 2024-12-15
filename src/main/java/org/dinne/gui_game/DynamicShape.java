package org.dinne.gui_game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class DynamicShape extends Rectangle {
    public DynamicShape() {} //what

    // used for pipe object
    public DynamicShape(int width) {
        super.setWidth(width);
        super.setHeight(500);
    }

    // mainly for player
    public DynamicShape(int width, int height) {
        super.setWidth(width);
        super.setHeight(height);
    }

    public void setPosition(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    public abstract void setImage(Image image);
    public abstract void drawFrame();
}
