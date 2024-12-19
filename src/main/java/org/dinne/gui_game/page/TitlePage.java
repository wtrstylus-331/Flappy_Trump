/*
Program Name: TitlePage.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Create UI for when program runs for the first time,
allowing user to play the game
 */

package org.dinne.gui_game.page;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.dinne.gui_game.Main;
import org.dinne.gui_game.util.Constants;
import org.dinne.gui_game.util.Listener;

public class TitlePage extends Scene implements Constants, Listener {
    // Private variables for scene creation
    private Text title, splash, credit;
    private Button start;
    private Pane parent;
    private ImageView image;

    // Constructor to create scene
    public TitlePage(Parent parent, double w, double h) {
        super(parent, w, h);
        this.parent = (Pane) parent;

        this.createUIComponents();
        this.interact();
    }

    // Private method to create details for all UI components on instantiation
    private void createUIComponents() {
        this.title = new Text("Flappy Trump");
        this.title.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 80));
        this.title.setStroke(Color.BLACK);
        this.title.setStrokeWidth(2);
        this.title.setFill(Color.WHITE);
        this.title.setX(50);
        this.title.setY(100);

        this.splash = new Text("the worst flappy bird ripoff!");
        this.splash.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        this.splash.setStroke(Color.BLACK);
        this.splash.setStrokeWidth(1);
        this.splash.setRotate(-5);
        this.splash.setFill(Color.RED);
        this.splash.setX(220);
        this.splash.setY(130);

        this.start = new Button("Start");
        this.start.setPrefSize(150,90);
        this.start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        this.start.setFocusTraversable(false);
        this.start.setBackground(Background.EMPTY);
        this.start.setTranslateX(215);
        this.start.setTranslateY(220);

        this.credit = new Text("🎵 Pixel Peeker Polka - Kevin Macleod");
        this.credit.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        this.credit.setStroke(Color.BLACK);
        this.credit.setStrokeWidth(0.6);
        this.credit.setFill(Color.WHITE);
        this.credit.setX(WIDTH - 280);
        this.credit.setY(HEIGHT - 10);

        this.image = new ImageView(new Image(getClass().getResource("/org/dinne/gui_game/image3.png").toString()));
        this.image.setFitWidth(100);
        this.image.setFitHeight(100);
        this.image.setX(70);
        this.image.setY(100);

        parent.getChildren().addAll(this.title, this.splash, this.start, this.image, this.credit);
    } // end of createUIComponents()

    // Method implemented from Listener to handle start button events
    @Override
    public void interact() {
        // UI effect
        this.start.setOnMouseEntered(event -> {
            this.start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        });

        // UI Effect
        this.start.setOnMouseExited(event -> {
            this.start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        });

        // Load GamePage scene
        this.start.setOnMouseClicked(event -> {
            Main.stage.setScene(new GamePage(new Pane(), WIDTH, HEIGHT));
        });
    }
} // end of TitlePage class
