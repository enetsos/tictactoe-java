package ch.supsi.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AppFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("/tictactoe.fxml");
        if (fxml == null) {
            return;
        }

        String s = AppHello.hello();
        System.out.println(s);

        Parent root = FXMLLoader.load(fxml);
        Scene scene = new Scene(root, 600, 629);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
