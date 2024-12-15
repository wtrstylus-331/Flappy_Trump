package org.dinne.gui_game;

import javafx.scene.image.Image;

public interface Constants {
    double GRAVITY = 1.01; // adds to player's yVelocity

    Image IMG_1 = new Image(Constants.class.getResourceAsStream("image1.png"));
    Image IMG_2 = new Image(Constants.class.getResourceAsStream("image2.png"));
    Image IMG_3 = new Image(Constants.class.getResourceAsStream("image3.png"));
    Image IMG_WALL = new Image(Constants.class.getResourceAsStream("wall.png"));
}
