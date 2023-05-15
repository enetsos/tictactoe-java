package ch.supsi.tictactoe;

import ch.supsi.tictactoe.controller.LocalizationController;
import ch.supsi.tictactoe.controller.PlayerInteractionsController;
import ch.supsi.tictactoe.model.Game;
import ch.supsi.tictactoe.model.LocalizationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppFx extends Application {

    private LocalizationController localizationController;
    private PlayerInteractionsController playerInteractionsController;

    public AppFx(){
        LocalizationModel localizationModel = LocalizationModel.getInstance();
        localizationModel.init("i18n.resources", Locale.forLanguageTag("en-EN"));
        localizationController = new LocalizationController(localizationModel);
    }
    @Override
    public void start(Stage stage) throws Exception {

        URL fxml = getClass().getResource("/tictactoe.fxml");
        if (fxml == null) {
            return;
        }

        Game game = new Game();
        FXMLLoader loader = new FXMLLoader(fxml, localizationController.getResourceBundle());

        Parent root = loader.load();
        playerInteractionsController = loader.getController();
        playerInteractionsController.setGame(game);
        game.addListener(playerInteractionsController);
        game.addGameLogicListener(playerInteractionsController);

        Scene scene = new Scene(root, 600, 650);
        stage.setResizable(false);

        playerInteractionsController.setScene(scene);

        stage.setOnCloseRequest(e -> {
            e.consume();
            playerInteractionsController.quit(null);
        });

        Label label = (Label) scene.lookup("#statusBar");
        label.setText("Welcome to Tic Tac Toe! I'ts your turn.");

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
