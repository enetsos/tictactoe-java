package ch.supsi.tictactoe.view;

import ch.supsi.tictactoe.model.LocalizationHelper;
import javafx.scene.control.Alert;

public class About {

    private static String name = "Tic Tac Toe";
    private static String[] authors = {
            "Matteo Arena",
            "Walter Sostene Losa"
    };
    private static String version = "0.0.1";

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

