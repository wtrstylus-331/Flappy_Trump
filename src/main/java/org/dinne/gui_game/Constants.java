package org.dinne.gui_game;

import javafx.scene.image.Image;

public interface Constants {
    int WIDTH = 600;
    int HEIGHT = 400;

    double GRAVITY = 1.01; // adds to player's yVelocity
    int PIPE_MIN = -120;
    int PIPE_MAX = 50;

    Image IMG_1 = new Image(Constants.class.getResourceAsStream("image1.png"));
    Image IMG_2 = new Image(Constants.class.getResourceAsStream("image2.png"));
    Image IMG_3 = new Image(Constants.class.getResourceAsStream("image3.png"));
    Image IMG_4 = new Image(Constants.class.getResourceAsStream("image4.png"));
    Image IMG_5 = new Image(Constants.class.getResourceAsStream("image5.png"));
    Image IMG_WALL = new Image(Constants.class.getResourceAsStream("wall.png"));
}
