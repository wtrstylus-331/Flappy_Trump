module org.dinne.gui_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.dinne.gui_game to javafx.fxml;
    exports org.dinne.gui_game;
}