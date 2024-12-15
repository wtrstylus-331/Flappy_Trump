package org.dinne.gui_game.page;

import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.object.PipeObject;
import org.dinne.gui_game.object.Player;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.Listener;

import java.util.ArrayList;
import java.util.Random;

public class GamePage extends Scene implements Constants, Listener {
    public static Player player = null;
    private Random random = new Random();
    private Pane parent;
    private ArrayList<PipeObject> pipes = new ArrayList<>();
    private Image image = IMG_BG;

    public GamePage(Parent parent, double v, double v1) {
        super(parent, v, v1);
        player = new Player(35,35);
        this.parent = (Pane) parent;

        if (!(player == null)) {
            player.setYVelocity(0.1);
            player.draw();

            this.parent.getChildren().add(player);
        }

        interact();
        drawPipes();
        setBackground();
    }

    private void setBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.DEFAULT.getWidth(),
                        BackgroundSize.DEFAULT.getHeight(),true,true,true,true)
        );

        parent.setBackground(new Background(backgroundImage));
    }

    private PipeObject createPipe() {
        PipeObject pipe = new PipeObject(45);
        pipe.setXVelocity(1.0);
        pipe.setXPos(WIDTH);
        pipe.setYPos(random.nextInt(PIPE_MIN, PIPE_MAX + 1));

        return pipe;
    }

    private void drawPipes() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2.75));
        pause.play();

        pause.setOnFinished(event -> {
            pause.stop();

            PipeObject pipe = createPipe();
            pipe.draw();
            pipes.add(pipe);
            parent.getChildren().add(pipe.getHolder());

            if (pipes.size() < 4) {
                pause.play();
            }
        });
    }

    @Override
    public void interact() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                while (true) {
                    int choice = random.nextInt(0,5);

                    if (!Main.sprites.get(choice).equals(player.getCurrentImage())) {
                        player.setImage(Main.sprites.get(choice));
                        break;
                    }
                }

                player.setYVelocity(-2.7);
            }
        });
    }
}
