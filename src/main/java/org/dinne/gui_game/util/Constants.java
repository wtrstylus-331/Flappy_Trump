package org.dinne.gui_game.util;

import javafx.scene.image.Image;

public interface Constants {
    int WIDTH = 600;
    int HEIGHT = 400;

    double GRAVITY = 1.01; // adds to player's yVelocity
    int PIPE_MIN = -120;
    int PIPE_MAX = 50;

    Image IMG_1 = new Image(Constants.class.getResource("/org/dinne/gui_game/image1.png").toString());
    Image IMG_2 = new Image(Constants.class.getResource("/org/dinne/gui_game/image2.png").toString());
    Image IMG_3 = new Image(Constants.class.getResource("/org/dinne/gui_game/image3.png").toString());
    Image IMG_4 = new Image(Constants.class.getResource("/org/dinne/gui_game/image4.png").toString());
    Image IMG_5 = new Image(Constants.class.getResource("/org/dinne/gui_game/image5.png").toString());
    Image IMG_WALL = new Image(Constants.class.getResource("/org/dinne/gui_game/wall.png").toString());
    Image IMG_BG = new Image(Constants.class.getResource("/org/dinne/gui_game/background.jpg").toString());
}
