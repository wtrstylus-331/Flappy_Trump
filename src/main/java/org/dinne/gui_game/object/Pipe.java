/*
Program Name: Pipe.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Custom Pipe class to be implemented into entire PipeObject class
 */

package org.dinne.gui_game.object;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Pipe extends Rectangle {
    // Constructor to set width and default height
    public Pipe(int width) {
        super(width, 400);
    }

    // Method to set image of the Pipe (rectangle)
    public void setImage(Image image) {
        super.setFill(new ImagePattern(image));
    }
} // end of Pipe class
