package org.dinne.gui_game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class PipeObject extends DynamicShape implements Constants {

    public PipeObject(int width) {
        super(width);

        this.setImage(IMG_WALL);
    }

    @Override
    public void setImage(Image image) {
        this.setFill(new ImagePattern(image));
    }

    @Override
    public void draw() {

    }
}
