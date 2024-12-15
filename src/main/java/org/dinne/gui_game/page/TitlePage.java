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
    Text title, splash;
    Button start;
    Pane parent;
    ImageView image;

    public TitlePage(Parent parent, double w, double h) {
        super(parent, w, h);
        this.parent = (Pane) parent;

        createUIComponents();
        interact();
    }

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

        this.image = new ImageView(new Image(getClass().getResource("/org/dinne/gui_game/image3.png").toString()));
        this.image.setFitWidth(100);
        this.image.setFitHeight(100);
        this.image.setX(70);
        this.image.setY(100);

        parent.getChildren().addAll(this.title, this.splash, this.start, this.image);
    }

    @Override
    public void interact() {
        this.start.setOnMouseEntered(event -> {
            this.start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        });

        this.start.setOnMouseExited(event -> {
            this.start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        });

        this.start.setOnMouseClicked(event -> {
            Main.stage.setScene(new GamePage(new Pane(), WIDTH, HEIGHT));
        });
    }
}
