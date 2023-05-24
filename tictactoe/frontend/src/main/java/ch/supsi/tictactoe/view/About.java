package ch.supsi.tictactoe.view;

import ch.supsi.tictactoe.model.LocalizationHelper;
import javafx.scene.control.Alert;

public class About {

    public static void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LocalizationHelper.getString("about"));
        alert.setHeaderText(LocalizationHelper.getString("aboutttt"));
        alert.setContentText("Tic Tac Toe v 1.0.0\n" +
                "Matteo Arena\n" +
                "Walter Sostene Losa");
        alert.showAndWait();
    }
}

