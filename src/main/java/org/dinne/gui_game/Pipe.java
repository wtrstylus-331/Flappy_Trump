package org.dinne.gui_game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Pipe extends Rectangle {
    public Pipe(int width) {
        super(width, 400);
    }

    public void setImage(Image image) {
        super.setFill(new ImagePattern(image));
    }
}
