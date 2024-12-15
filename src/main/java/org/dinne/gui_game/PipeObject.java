package org.dinne.gui_game;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PipeObject extends DynamicShape implements Constants {
    private Pipe top, down;
    private Pane holder;
    private AnimationTimer timer;
    private double xVelocity;
    private int width;

    public PipeObject(int width) {
        super(width);
        super.setHeight(0);
        this.width = width;

        holder = new Pane();
        holder.setPrefWidth(width);
        holder.setPrefHeight(1000);

        top = new Pipe(width);
        down = new Pipe(width);
        top.setY(-250);
        top.setImage(IMG_WALL);
        down.setY(250);
        down.setImage(IMG_WALL);

        holder.getChildren().addAll(top, down);
    }

    public void setXPos(double xPos) {
        holder.setTranslateX(xPos);
    }

    public void setYPos(double yPos) {
        holder.setTranslateY(yPos);
    }

    public double getXPos() {
        return holder.getTranslateX();
    }

    public double getYPos() {
        return holder.getTranslateY();
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public Pane getHolder() {
        return holder;
    }

    public boolean isColliding(Player player) {
        double playerX = player.getX() + player.getTranslateX();
        double playerY = player.getY() + player.getTranslateY();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();

        double topX = holder.getTranslateX();
        double topY = holder.getTranslateY() + top.getY();
        double topWidth = top.getWidth();
        double topHeight = top.getHeight();

        double downX = holder.getTranslateX();
        double downY = holder.getTranslateY() + down.getY();
        double downWidth = down.getWidth();
        double downHeight = down.getHeight();

        boolean collidingTop = playerX < topX + topWidth && playerX + playerWidth > topX
                && playerY < topY + topHeight && playerY + playerHeight > topY;

        boolean collidingDown = playerX < downX + downWidth && playerX + playerWidth > downX
                && playerY < downY + downHeight && playerY + playerHeight > downY;

        return collidingTop || collidingDown;
    }


    @Override
    public void draw() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                holder.setTranslateX(holder.getTranslateX() - xVelocity);

                if (holder.getTranslateX() <= -width) {
                    holder.setTranslateX(WIDTH);
                }

                if (isColliding(Main.plr)) {
                    System.out.println("colliding");
                }
            }
        };

        timer.start();
    }
}
