package org.dinne.gui_game.page;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.object.Player;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.Listener;

import java.util.Random;

public class GamePage extends Scene implements Constants, Listener {
    public static Player player = null;
    private Random random = new Random();
    private Pane parent;

    public GamePage(Parent parent, double v, double v1) {
        super(parent, v, v1);
        player = new Player(40,40);
        this.parent = (Pane) parent;

        if (!(player == null)) {
            player.setYVelocity(0.1);
            player.draw();

            this.parent.getChildren().add(player);
        }

        interact();
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
