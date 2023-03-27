package ch.supsi.tictactoe;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class About {

    private static String name = "Tic Tac Toe";
    private static String[] authors = {
            "Matteo Arena",
            "Walter Sostene Losa"
    };
    private static String version = "0.0.1";

    public static void showAbout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About Tic Tac Toe");
        alert.setContentText("Tic Tac Toe v0.0.1\n" +
                "Matteo Arena\n" +
                "Walter Sostene Losa");
        alert.showAndWait();
    }
}
