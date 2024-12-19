/*
Program Name: Constants.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Interface storing constant variables
 */

package org.dinne.gui_game.util;

import javafx.scene.image.Image;

public interface Constants {
    int WIDTH = 600; // Window width
    int HEIGHT = 400; // Window height

    double GRAVITY = 1.01; // adds to player's yVelocity
    int PIPE_MIN = -120; // Y-constraint to spawn pipe
    int PIPE_MAX = 50; // Y-constraint to spawn pipe

    // Media references
    Image IMG_WALL = new Image(Constants.class.getResource("/org/dinne/gui_game/wall.png").toString());
    Image IMG_BG = new Image(Constants.class.getResource("/org/dinne/gui_game/background.jpg").toString());
} // end of Constants interface