package ch.supsi.tictactoe.view;

import ch.supsi.tictactoe.controller.EditSymbolsController;
import ch.supsi.tictactoe.controller.LocalizationController;
import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.LocalizationModel;
import ch.supsi.tictactoe.model.SettingsModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EditSymbols {

    private final Parent root;
    private LocalizationController localizationController;
    private EditSymbolsController editSymbolsController;
    private static boolean isOpen = false;
    private SettingsModel settings;
    private GameListener listener;

    public EditSymbols(SettingsModel settings, GameListener listener) {
        this.settings = settings;
        this.listener = listener;
        URL symbolMenuFxmlUrl = getClass().getResource("/editsymbols.fxml");
        if (symbolMenuFxmlUrl == null) {
            throw new RuntimeException("editsymbols.fxml not found");
        }

        LocalizationModel localizationModel = LocalizationModel.getInstance();
        localizationModel.init("i18n.resources", Locale.forLanguageTag(settings.getLanguage()));
        localizationController = new LocalizationController(localizationModel);

        FXMLLoader symbolMenuLoader = new FXMLLoader(symbolMenuFxmlUrl, localizationController.getResourceBundle());
        editSymbolsController = new EditSymbolsController();

        symbolMenuLoader.setControllerFactory(c -> editSymbolsController);

        try {
            root = symbolMenuLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }



    public void show() {
        if(isOpen)
            return;
        isOpen = true;
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> isOpen = false);

        editSymbolsController.setSettings(settings);
        editSymbolsController.setListener(listener);

        stage.show();
    }

}