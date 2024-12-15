module org.dinne.gui_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.dinne.gui_game to javafx.fxml;
    exports org.dinne.gui_game;
    exports org.dinne.gui_game.util;
    opens org.dinne.gui_game.util to javafx.fxml;
    exports org.dinne.gui_game.object;
    opens org.dinne.gui_game.object to javafx.fxml;
    exports org.dinne.gui_game.page;
    opens org.dinne.gui_game.page to javafx.fxml;
}