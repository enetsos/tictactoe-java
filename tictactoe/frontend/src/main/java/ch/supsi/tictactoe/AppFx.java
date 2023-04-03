package ch.supsi.tictactoe;

import ch.supsi.tictactoe.controller.PlayerInteractionsController;
import ch.supsi.tictactoe.model.Game;
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

        Game game = new Game();

        FXMLLoader loader = new FXMLLoader(fxml);

        Parent root = loader.load();
        ((PlayerInteractionsController)loader.getController()).setGame(game);

        Scene scene = new Scene(root, 600, 629);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
