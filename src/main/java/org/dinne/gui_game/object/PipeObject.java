/*
Program Name: PipeObject.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: PipeObject class to hold Pipe objects and handle collision
between Pipes and Player
 */

package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.page.GamePage;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.DynamicShape;

import java.util.Random;

public class PipeObject extends DynamicShape implements Constants {
    // Private variables, Pane to hold top and down Pipe objects and timer to draw
    private Pipe top, down;
    private Pane holder;
    private AnimationTimer timer;
    private double xVelocity;
    private int width;

    // Boolean flag
    private boolean scoreUpdated;

    private Random random = new Random();

    // Constructor to set details of PipeObject
    public PipeObject(int width) {
        super(width);
        super.setHeight(0);
        this.width = width;

        // Create Pane to hold 2 Pipe objects
        this.holder = new Pane();
        this.holder.setPrefWidth(width);
        this.holder.setPrefHeight(1000);

        // Set positions of Pipe objects
        this.top = new Pipe(width);
        this.down = new Pipe(width);
        this.top.setY(-245);
        this.top.setImage(IMG_WALL);
        this.down.setY(260);
        this.down.setImage(IMG_WALL);

        // Add Pipe objects to the Pane
        this.holder.getChildren().addAll(top, down);
    }

    // Method to set PipeObject X position
    public void setXPos(double xPos) {
        this.holder.setTranslateX(xPos);
    }

    // Method to set PipeObject Y position
    public void setYPos(double yPos) {
        this.holder.setTranslateY(yPos);
    }

    // Method to get current PipeObject X position
    public double getXPos() {
        return this.holder.getTranslateX();
    }

    // Method to get current PipeObject Y position
    public double getYPos() {
        return this.holder.getTranslateY();
    }

    // Method to set PipeObject X velocity
    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Method to get current PipeObject X velocity
    public double getxVelocity() {
        return this.xVelocity;
    }

    // Method to return current Pane holding upper and lower Pipe objects
    public Pane getHolder() {
        return this.holder;
    }

    // Method to calculate collisions
    public boolean isColliding(Player player) {
        // Get Player details
        double playerX = player.getX() + player.getTranslateX();
        double playerY = player.getY() + player.getTranslateY();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();

        // Get details of upper Pipe object
        double topX = this.holder.getTranslateX();
        double topY = this.holder.getTranslateY() + this.top.getY();
        double topWidth = this.top.getWidth();
        double topHeight = this.top.getHeight();

        // Get details of lower Pipe object
        double downX = this.holder.getTranslateX();
        double downY = this.holder.getTranslateY() + this.down.getY();
        double downWidth = this.down.getWidth();
        double downHeight = this.down.getHeight();

        // Conditional for upper collision
        boolean collidingTop = playerX < topX + topWidth && playerX + playerWidth > topX
                && playerY < topY + topHeight && playerY + playerHeight > topY;

        // Conditional for lower collision
        boolean collidingDown = playerX < downX + downWidth && playerX + playerWidth > downX
                && playerY < downY + downHeight && playerY + playerHeight > downY;

        return collidingTop || collidingDown;
    } // end of isColliding() method

    // Method to immobilize PipeObject
    public void stop() {
        this.timer.stop();
        this.holder.setTranslateX(this.holder.getTranslateX());
    }

    // Method implemented to draw PipeObject every frame
    @Override
    public void draw() {
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Set X velocity
                holder.setTranslateX(holder.getTranslateX() - xVelocity);

                // When game is over stop all pipes
                if (!Main.gameRunning) {
                    timer.stop();
                    for (PipeObject p : GamePage.pipes) {
                        p.stop();
                    }
                }

                // If PipeObject is past window width bounds reset to right side of window
                if (holder.getTranslateX() <= -width) {
                    holder.setTranslateX(WIDTH);
                    holder.setTranslateY(random.nextInt(PIPE_MIN, PIPE_MAX + 1));
                    scoreUpdated = false;
                }

                // Game over when PipeObject collides with Player
                if (!(GamePage.player == null)) {
                    if (isColliding(GamePage.player)) {
                        System.out.println("colliding");
                        Main.gameRunning = false;
                    }
                }

                // If Player is past PipeObject X position increment score
                if (!scoreUpdated && GamePage.player.getX() > holder.getTranslateX()) {
                    Main.currentScore++;
                    GamePage.player.playScoreAudio();
                    GamePage.updateScores();
                    scoreUpdated = true;
                }
            }
        };

        // Start timer
        this.timer.start();
    } // end of draw() method
} // end of PipeObject class
