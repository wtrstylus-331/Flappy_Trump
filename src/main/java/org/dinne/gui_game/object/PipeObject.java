package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import org.dinne.gui_game.page.GamePage;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.DynamicShape;

import java.util.Random;

public class PipeObject extends DynamicShape implements Constants {
    private Pipe top, down;
    private Pane holder;
    private AnimationTimer timer;
    private double xVelocity;
    private int width;

    private Random random = new Random();

    public PipeObject(int width) {
        super(width);
        super.setHeight(0);
        this.width = width;

        holder = new Pane();
        holder.setPrefWidth(width);
        holder.setPrefHeight(1000);

        top = new Pipe(width);
        down = new Pipe(width);
        top.setY(-245);
        top.setImage(IMG_WALL);
        down.setY(260);
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
                    holder.setTranslateY(random.nextInt(PIPE_MIN, PIPE_MAX + 1));
                }

                if (!(GamePage.player == null)) {
                    if (isColliding(GamePage.player)) {
                        System.out.println("colliding");
                    }
                }
            }
        };

        timer.start();
    }
}
