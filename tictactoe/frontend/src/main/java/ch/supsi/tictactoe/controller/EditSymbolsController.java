package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.GameLogic;
import ch.supsi.tictactoe.model.SettingsSaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import java.util.Collections;

public class EditSymbolsController {
    private GameLogic logic;
    private GameListener listener;
    private SettingsSaver saver = new SettingsSaver();
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

        aiCombo.getItems().add(logic.getUserChar());
        Collections.sort(aiCombo.getItems());

        aiCombo.getItems().remove(val-65);

        logic.setUserChar(val);
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

        userCombo.getItems().add(logic.getAiChar());
        Collections.sort(userCombo.getItems());

        userCombo.getItems().remove(val-65);

        logic.setAIChar(val);
        saveSettings();
        listener.update();
        finishedCombo = true;
    }

    @FXML
    public void changeUserColor(ActionEvent actionEvent) {
        logic.setUserColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saveSettings();
        listener.update();
    }

    @FXML
    public void changeAiColor(ActionEvent actionEvent) {
        logic.setAiColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saveSettings();
        listener.update();
    }

    // =========== METHODS ===========

    public void setListener(GameListener listener){
        this.listener = listener;
    }


    public void setGameLogic(GameLogic logic){
        this.logic = logic;

        userCombo.getItems().addAll(characters);
        aiCombo.getItems().addAll(characters);

        aiCombo.getItems().remove(logic.getUserChar() - 65);
        userCombo.getItems().remove(logic.getAiChar() - 65);

        userCombo.setValue(logic.getUserChar());
        aiCombo.setValue(logic.getAiChar());

        userColorPicker.setValue(Color.web(logic.getUserColor()));
        aiColorPicker.setValue(Color.web(logic.getAiColor()));
    }

    private void saveSettings(){
        saver.save(logic);
    }



}

