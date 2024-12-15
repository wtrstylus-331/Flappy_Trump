package org.dinne.gui_game;

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
        title = new Text("Flappy Trump");
        title.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 80));
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        title.setFill(Color.WHITE);
        title.setX(50);
        title.setY(100);

        splash = new Text("the worst flappy bird ripoff!");
        splash.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        splash.setStroke(Color.BLACK);
        splash.setStrokeWidth(1);
        splash.setRotate(-5);
        splash.setFill(Color.RED);
        splash.setX(220);
        splash.setY(130);

        start = new Button("Start");
        start.setPrefSize(150,90);
        start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        start.setFocusTraversable(false);
        start.setBackground(Background.EMPTY);
        start.setTranslateX(215);
        start.setTranslateY(220);

        image = new ImageView(new Image(getClass().getResourceAsStream("image3.png")));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setX(70);
        image.setY(100);

        parent.getChildren().addAll(title, splash, start, image);
    }

    @Override
    public void interact() {
        start.setOnMouseEntered(event -> {
            start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 40));
        });

        start.setOnMouseExited(event -> {
            start.setFont(Font.font("arial", FontWeight.NORMAL, FontPosture.REGULAR, 30));
        });

        start.setOnMouseClicked(event -> {
            Main.stage.setScene(new GamePage(new Pane(), WIDTH, HEIGHT));
        });
    }
}
