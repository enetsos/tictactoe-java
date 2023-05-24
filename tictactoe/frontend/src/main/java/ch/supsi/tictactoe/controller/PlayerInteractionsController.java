package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.gamelogic.Game;
import ch.supsi.tictactoe.gamelogic.GameResult;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.view.About;
import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.*;
import ch.supsi.tictactoe.view.EditSymbols;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class PlayerInteractionsController implements GameListener {
    private Game game;
    private Scene scene;
    private File file;
    private SettingsModel settingsModel;

    @FXML
    private Label statusBar;

    public void setGame(Game game){
        this.game = game;
        game.setListener(this);
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    // =========== FXML ===========

    @FXML
    public void newGame(ActionEvent e) {
        //New Game
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(LocalizationHelper.getString("new"));
        alert.setHeaderText(LocalizationHelper.getString("askNewGame"));
        ButtonType saveAndNewGame = new ButtonType(LocalizationHelper.getString("save"));
        ButtonType newGame = new ButtonType(LocalizationHelper.getString("dontSave"));
        ButtonType cancel = new ButtonType(LocalizationHelper.getString("cancel"));
        alert.getButtonTypes().setAll(saveAndNewGame, newGame, cancel);
        alert.showAndWait();

        String choice = alert.getResult().getText();

        if(choice.equals(LocalizationHelper.getString("cancel"))){
            return;
        }else if(choice.equals(LocalizationHelper.getString("save"))){
            saveGame(e);
        }
        statusBar.setText(LocalizationHelper.getString("info.newGame"));

        game.newGame();
        update();
    }

    @FXML
    public void openGame(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(LocalizationHelper.getString("opengame"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Tic Tac Toe Game", "*.ttt")
        );
        File file = fileChooser.showOpenDialog(scene.getWindow());
        if(file != null){
            this.file = file;
            game.loadGame(file);
        }
    }

    @FXML
    public void saveGame(ActionEvent e) {
        if(file != null){
            game.saveGame(file);
        }else{
            saveGameAs(e);
        }
    }

    @FXML
    public void saveGameAs(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(LocalizationHelper.getString("savegame"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Tic Tac Toe Game", "*.ttt")
        );
        File file = fileChooser.showSaveDialog(scene.getWindow());
        if(file != null){
            this.file = file;
            game.saveGame(file);
        }
    }

    @FXML
    public void quit(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(LocalizationHelper.getString("quit"));
        alert.setHeaderText(LocalizationHelper.getString("askQuit"));
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            Platform.exit();
        }
    }

    @FXML
    public void editSymbols(ActionEvent e) {
        EditSymbols es = new EditSymbols(settingsModel, this);
        es.show();
    }

    @FXML
    public void editLanguage(ActionEvent e) {
        String id = e.getSource().toString().toLowerCase().substring(12, 14);
        if(id.equals("en")){
            settingsModel.setLanguage("en-EN");
        }else if(id.equals("it")){
            settingsModel.setLanguage("it-IT");
        }
        settingsModel.save();
    }

    @FXML
    public void showAbout(ActionEvent e) {
        About.showAbout();
    }

    @FXML
    public void playerAction(ActionEvent e) {
        if(game == null){
            return;
        }
        Button button = (Button) e.getSource();

        statusBar.setText(LocalizationHelper.getString("info.yourTurn"));
        String id = button.getId();
        int row = Integer.parseInt(id.substring(1, 2));
        int col = Integer.parseInt(id.substring(2, 3));
        if(!game.userPlay(row, col)){
            statusBar.setText(LocalizationHelper.getString("info.wrongCell"));
            return;
        }
        update();
    }



    // =========== GameListener ===========
    @Override
    public void update(){
        Player[][] gameMatrix = game.getGameLogic().getGameMatrix();



        ThemeModel theme = settingsModel.getTheme();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b = (Button) scene.lookup("#b" + i + j);
                if(gameMatrix[i][j] == null) {
                    b.setText("");
                }else{
                    if(gameMatrix[i][j].isUser()){
                        b.setText(String.valueOf(theme.getUserSymbol()));
                        b.setTextFill(Color.web(theme.getUserColor()));
                    }else if(gameMatrix[i][j].isAI()){
                        b.setText(String.valueOf(theme.getAiSymbol()));
                        b.setTextFill(Color.web(theme.getAiColor()));
                    }
                }
            }
        }
    }

    @Override
    public void gameOver(GameResult result) {
        switch (result){
            case WIN:
                statusBar.setText(LocalizationHelper.getString("info.youWin"));
                break;
            case LOSE:
                statusBar.setText(LocalizationHelper.getString("info.aiWin"));
                break;
            case DRAW:
                statusBar.setText(LocalizationHelper.getString("info.draw"));
                break;
        }
        gameEnded();
    }

    // =========== Public Methods ===========

    public void setSettingsModel(SettingsModel settingsModel){
        this.settingsModel = settingsModel;
    }


    // =========== Private Methods ===========
    private void gameEnded(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(LocalizationHelper.getString("gameOver"));
        alert.setHeaderText(LocalizationHelper.getString("doYouWantStartNew"));
        ButtonType yes = new ButtonType(LocalizationHelper.getString("yes"));
        ButtonType no = new ButtonType(LocalizationHelper.getString("no"));
        ButtonType quit = new ButtonType(LocalizationHelper.getString("quit"));
        alert.getButtonTypes().setAll(yes, no, quit);
        alert.showAndWait();
        if(alert.getResult().getText().equals(LocalizationHelper.getString("yes"))){
            game.newGame();
            statusBar.setText(LocalizationHelper.getString("info.newGame"));
        }
        if(alert.getResult().getText().equals(LocalizationHelper.getString("quit"))){
            quit(null);
        }
    }
}
