package org.dinne.gui_game.object;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import org.dinne.gui_game.Main;
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
    private boolean scoreUpdated;

    private Random random = new Random();

    public PipeObject(int width) {
        super(width);
        super.setHeight(0);
        this.width = width;

        this.holder = new Pane();
        this.holder.setPrefWidth(width);
        this.holder.setPrefHeight(1000);

        this.top = new Pipe(width);
        this.down = new Pipe(width);
        this.top.setY(-245);
        this.top.setImage(IMG_WALL);
        this.down.setY(260);
        this.down.setImage(IMG_WALL);

        this.holder.getChildren().addAll(top, down);
    }

    public void setXPos(double xPos) {
        this.holder.setTranslateX(xPos);
    }

    public void setYPos(double yPos) {
        this.holder.setTranslateY(yPos);
    }

    public double getXPos() {
        return this.holder.getTranslateX();
    }

    public double getYPos() {
        return this.holder.getTranslateY();
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getxVelocity() {
        return this.xVelocity;
    }

    public Pane getHolder() {
        return this.holder;
    }

    public boolean isColliding(Player player) {
        double playerX = player.getX() + player.getTranslateX();
        double playerY = player.getY() + player.getTranslateY();
        double playerWidth = player.getWidth();
        double playerHeight = player.getHeight();

        double topX = this.holder.getTranslateX();
        double topY = this.holder.getTranslateY() + this.top.getY();
        double topWidth = this.top.getWidth();
        double topHeight = this.top.getHeight();

        double downX = this.holder.getTranslateX();
        double downY = this.holder.getTranslateY() + this.down.getY();
        double downWidth = this.down.getWidth();
        double downHeight = this.down.getHeight();

        boolean collidingTop = playerX < topX + topWidth && playerX + playerWidth > topX
                && playerY < topY + topHeight && playerY + playerHeight > topY;

        boolean collidingDown = playerX < downX + downWidth && playerX + playerWidth > downX
                && playerY < downY + downHeight && playerY + playerHeight > downY;

        return collidingTop || collidingDown;
    }

    public void stop() {
        this.timer.stop();
        this.holder.setTranslateX(this.holder.getTranslateX());
    }

    @Override
    public void draw() {
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                holder.setTranslateX(holder.getTranslateX() - xVelocity);

                if (!Main.gameRunning) {
                    timer.stop();
                    for (PipeObject p : GamePage.pipes) {
                        p.stop();
                    }
                }

                if (holder.getTranslateX() <= -width) {
                    holder.setTranslateX(WIDTH);
                    holder.setTranslateY(random.nextInt(PIPE_MIN, PIPE_MAX + 1));
                    scoreUpdated = false;
                }

                if (!(GamePage.player == null)) {
                    if (isColliding(GamePage.player)) {
                        System.out.println("colliding");
                        Main.gameRunning = false;
                    }
                }

                if (!scoreUpdated && GamePage.player.getX() > holder.getTranslateX()) {
                    Main.currentScore++;
                    GamePage.player.playScoreAudio();
                    GamePage.updateScores();
                    scoreUpdated = true;
                }
            }
        };

        this.timer.start();
    }
}
