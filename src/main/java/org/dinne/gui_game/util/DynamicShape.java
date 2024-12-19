/*
Program Name: DynamicShape.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Abstract class extending Rectangle class to serve as the core
for Player and PipeObject, having abstract method to be able to draw
logic related to object's position every frame via AnimationTimer
 */

package org.dinne.gui_game.util;

import javafx.scene.shape.Rectangle;

public abstract class DynamicShape extends Rectangle {
    // Constructor used for PipeObject as height will be created within that class
    public DynamicShape(int width) {
        super.setWidth(width);
    }

    // Constructor used for Player dimensions
    public DynamicShape(int width, int height) {
        super.setWidth(width);
        super.setHeight(height);
    }

    // Method to set object's position
    public void setPosition(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    // Abstract method to implement drawing every frame
    public abstract void draw();
} // end of DynamicShape abstract class
