package ch.supsi.tictactoe;

import ch.supsi.tictactoe.controller.PlayerInteractionsController;
import ch.supsi.tictactoe.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        game.addListener(loader.getController());
        game.addGameLogicListener(loader.getController());

        Scene scene = new Scene(root, 600, 629);
        stage.setResizable(false);

        ((PlayerInteractionsController)loader.getController()).setScene(scene);

        stage.setScene(scene);
        stage.show();

        //Welcome Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText("Welcome to Tic Tac Toe!");
        alert.setContentText("Click on the board to place your symbol.");
        alert.showAndWait();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
