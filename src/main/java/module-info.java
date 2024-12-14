module org.dinne.gui_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.dinne.gui_game to javafx.fxml;
    exports org.dinne.gui_game;
}