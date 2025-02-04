package ch.supsi.tictactoe;

import ch.supsi.tictactoe.controller.LocalizationController;
import ch.supsi.tictactoe.controller.PlayerInteractionsController;
import ch.supsi.tictactoe.gamelogic.Game;
import ch.supsi.tictactoe.gamelogic.GameLogic;
import ch.supsi.tictactoe.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;

public class AppFx extends Application {

    private LocalizationController localizationController;
    private PlayerInteractionsController playerInteractionsController;
    private GameLogic logic = new GameLogic();
    private SettingsModel settingsModel = new SettingsModel();

    public AppFx(){

        settingsModel.load();





        //SettingsSaver s = new SettingsSaver();
        //s.load(logic);

        LocalizationModel localizationModel = LocalizationModel.getInstance();
        localizationModel.init("i18n.resources", Locale.forLanguageTag(settingsModel.getLanguage()));
        localizationController = new LocalizationController(localizationModel);
    }
    @Override
    public void start(Stage stage) throws Exception {

        URL fxml = getClass().getResource("/tictactoe.fxml");
        if (fxml == null) {
            return;
        }

        Game game = new Game(logic);
        FXMLLoader loader = new FXMLLoader(fxml, localizationController.getResourceBundle());

        Parent root = loader.load();
        playerInteractionsController = loader.getController();
        playerInteractionsController.setGame(game);
        playerInteractionsController.setSettingsModel(settingsModel);

        Scene scene = new Scene(root, 600, 650);
        stage.setResizable(false);

        playerInteractionsController.setScene(scene);

        stage.setOnCloseRequest(e -> {
            e.consume();
            playerInteractionsController.quit(null);
        });

        Label label = (Label) scene.lookup("#statusBar");
        label.setText(LocalizationHelper.getString("info.newGame"));

        stage.setScene(scene);
        stage.show();


        //Welcome Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText(LocalizationHelper.getString("welcomeMessage"));
        alert.setContentText(LocalizationHelper.getString("clickBoard"));
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
