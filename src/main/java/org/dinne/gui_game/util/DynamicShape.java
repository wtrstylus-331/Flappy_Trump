package org.dinne.gui_game.util;

import javafx.scene.shape.Rectangle;

public abstract class DynamicShape extends Rectangle {
    // used for pipe object
    public DynamicShape(int width) {
        super.setWidth(width);
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

    public abstract void draw();
}
