package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.controller.EditSymbolsController;
import ch.supsi.tictactoe.controller.LocalizationController;
import ch.supsi.tictactoe.listener.GameListener;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EditSymbolsModel {

    private final Parent root;
    private static Character[] characters;
    private LocalizationController localizationController;
    private EditSymbolsController editSymbolsController;
    private static boolean isOpen = false;

    public EditSymbolsModel(GameLogic logic, GameListener listener) {
        URL symbolMenuFxmlUrl = getClass().getResource("/editSymbols.fxml");
        if (symbolMenuFxmlUrl == null) {
            throw new RuntimeException("symbolmenu.fxml not found");
        }

        LocalizationModel localizationModel = LocalizationModel.getInstance();
        localizationModel.init("i18n.resources", Locale.forLanguageTag(logic.getCurrentLanguage()));
        localizationController = new LocalizationController(localizationModel);

        FXMLLoader symbolMenuLoader = new FXMLLoader(symbolMenuFxmlUrl, localizationController.getResourceBundle());
        editSymbolsController = new EditSymbolsController();
        editSymbolsController.setGameLogic(logic);
        editSymbolsController.setListener(listener);
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

        characters = new Character[26];
        for (int i = 0; i < characters.length; i++){
            characters[i] = (char)(65 + i);
        }

        ComboBox<Character> userBox = (ComboBox<Character>) scene.lookup("#userCombo");
        ComboBox<Character> aiBox = (ComboBox<Character>) scene.lookup("#aiCombo");

        userBox.getItems().addAll(characters);
        aiBox.getItems().addAll(characters);

        editSymbolsController.setCombos(userBox, aiBox);
        editSymbolsController.setColorPickers((ColorPicker) scene.lookup("#userColorPicker"), (ColorPicker) scene.lookup("#aiColorPicker"));

        stage.isAlwaysOnTop();
        stage.setOnCloseRequest(e -> isOpen = false);
        stage.show();
    }

}