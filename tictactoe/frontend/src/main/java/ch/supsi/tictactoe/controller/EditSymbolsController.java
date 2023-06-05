package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.SettingsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import java.util.Collections;

public class EditSymbolsController {
   // private GameLogic logic;
    private SettingsModel settingsModel;
    private GameListener listener;
    private Character[] characters = new Character[26];
    @FXML
    private ComboBox aiCombo;
    @FXML
    private ComboBox userCombo;
    @FXML
    private ColorPicker userColorPicker;
    @FXML
    private ColorPicker aiColorPicker;
    private boolean finishedCombo = true;


    // =========== CONSTRUCTOR ===========
    public EditSymbolsController(){
        for(int i = 0; i < 26; i++){
            characters[i] = (char)(i+65);
        }
    }

    // =========== FXML ===========
    @FXML
    public void userCharEdit(ActionEvent actionEvent) {
        if(!finishedCombo)
            return;
        finishedCombo = false;

        char val = (char)userCombo.getValue();

        aiCombo.getItems().add(settingsModel.getTheme().getUserSymbol());
        Collections.sort(aiCombo.getItems());

        aiCombo.getItems().remove(val-65);

        settingsModel.getTheme().setUserSymbol(val);
        saveSettings();
        listener.update();
        finishedCombo = true;
    }

    @FXML
    public void aiCharEdit(ActionEvent actionEvent) {
        if(!finishedCombo)
            return;
        finishedCombo = false;

        char val = (char)aiCombo.getValue();

        userCombo.getItems().add(settingsModel.getTheme().getAiSymbol());
        Collections.sort(userCombo.getItems());

        userCombo.getItems().remove(val-65);

        settingsModel.getTheme().setAiSymbol(val);
        saveSettings();
        listener.update();
        finishedCombo = true;
    }

    @FXML
    public void changeUserColor(ActionEvent actionEvent) {
        settingsModel.getTheme().setUserColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saveSettings();
        listener.update();
    }

    @FXML
    public void changeAiColor(ActionEvent actionEvent) {
        settingsModel.getTheme().setAiColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saveSettings();
        listener.update();
    }

    // =========== METHODS ===========

    public void setListener(GameListener listener){
        this.listener = listener;
    }


    public void setSettings(SettingsModel settings){
        this.settingsModel = settings;

        userCombo.getItems().addAll(characters);
        aiCombo.getItems().addAll(characters);

        aiCombo.getItems().remove(settingsModel.getTheme().getUserSymbol() - 65);
        userCombo.getItems().remove(settingsModel.getTheme().getAiSymbol() - 65);

        userCombo.setValue(settingsModel.getTheme().getUserSymbol());
        aiCombo.setValue(settingsModel.getTheme().getAiSymbol());

        userColorPicker.setValue(Color.web(settingsModel.getTheme().getUserColor()));
        aiColorPicker.setValue(Color.web(settingsModel.getTheme().getAiColor()));
    }

    private void saveSettings(){
        settingsModel.save();
    }



}

