package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.GameLogic;
import ch.supsi.tictactoe.model.SettingsSaver;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;

public class EditSymbolsController {
    private GameLogic logic;
    private GameListener listener;
    private SettingsSaver saver = new SettingsSaver();

    private boolean finishedCombo = true;

    public void userCharEdit(@NotNull ActionEvent actionEvent) {
        if(!finishedCombo)
            return;
        finishedCombo = false;

        ComboBox<Character> combo = (ComboBox<Character>)actionEvent.getSource();
        char val = combo.getValue();

        combo.getItems().add(logic.getUserChar());
        Collections.sort(combo.getItems());

        combo.getItems().remove(val-65);

        logic.setUserChar(val);
        saver.save(logic);
        listener.update();
        finishedCombo = true;
    }

    public void aiCharEdit(ActionEvent actionEvent) {
        if(!finishedCombo)
            return;
        finishedCombo = false;

        ComboBox<Character> combo = (ComboBox<Character>)actionEvent.getSource();
        char val = combo.getValue();

        combo.getItems().add(logic.getAiChar());
        Collections.sort(combo.getItems());

        combo.getItems().remove(val-65);

        logic.setAIChar(val);
        saver.save(logic);
        listener.update();
        finishedCombo = true;
    }

    public void setCombos(ComboBox<Character> user, ComboBox<Character> ai){
        user.setValue(logic.getUserChar());
        ai.setValue(logic.getAiChar());

        listener.update();
    }

    public void setGameLogic(GameLogic logic){
        this.logic = logic;
    }

    public void setListener(GameListener listener){
        this.listener = listener;
    }
    public void setColorPickers(ColorPicker user, ColorPicker ai){
        user.setValue(Color.web(logic.getUserColor()));
        ai.setValue(Color.web(logic.getAiColor()));
    }

    public void changeUserColor(ActionEvent actionEvent) {
        logic.setUserColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saver.save(logic);
        listener.update();
    }

    public void changeAiColor(ActionEvent actionEvent) {
        logic.setAiColor(((ColorPicker)actionEvent.getSource()).getValue().toString());
        saver.save(logic);
        listener.update();
    }
}

