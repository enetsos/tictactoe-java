package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.About;
import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PreferencesHandler;
import java.io.File;
import java.util.Locale;

public class PlayerInteractionsController implements GameListener, GameLogicListener {

    private Game game;
    private Scene scene;

    private File file;

    public void setGame(Game game){
        this.game = game;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }


    @FXML
    public void newGame(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game");
        alert.setHeaderText(LocalizationHelper.getString("askNewGame"));
        ButtonType saveAndNewGame = new ButtonType(LocalizationHelper.getString("save"));
        ButtonType newGame = new ButtonType(LocalizationHelper.getString("dontSave"));
        ButtonType cancel = new ButtonType(LocalizationHelper.getString("cancel"));
        alert.getButtonTypes().setAll(saveAndNewGame, newGame, cancel);
        alert.showAndWait();

        if(alert.getResult().getText().equals(LocalizationHelper.getString("save"))){
            saveGame(e);
            game.newGame();

            update();
            updateStatusBar(LocalizationHelper.getString("info.newGame"));
        }

        if(alert.getResult().getText().equals(LocalizationHelper.getString("dontSave"))){
            game.newGame();
            update();
            updateStatusBar(LocalizationHelper.getString("info.newGame"));
        }

    }

    @FXML
    public void openGame(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game");
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
        fileChooser.setTitle("Save Game");
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

        EditSymbolsModel esm = new EditSymbolsModel(game.getGameLogic(), this);
        esm.show();

        saveSettings();
        update();

    }

    @FXML
    public void editLanguage(ActionEvent e) {
        String id = e.getSource().toString().toLowerCase().substring(12, 14);
        if(id.equals("en")){
            game.getGameLogic().setLanguage("en-EN");
        }else if(id.equals("it")){
            game.getGameLogic().setLanguage("it-IT");
        }
        saveSettings();
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

        updateStatusBar(LocalizationHelper.getString("info.yourTurn"));
        String id = button.getId();
        int row = Integer.parseInt(id.substring(1, 2));
        int col = Integer.parseInt(id.substring(2, 3));
        game.getGameLogic().playerAction(row, col);
        update();

        if(game.getGameLogic().userWin())
            userWin();
        else if(game.getGameLogic().AIWin())
            aiWin();
        else if(game.getGameLogic().isDraw())
            allCellOccupied();

    }

    //update status bar
    public void updateStatusBar(String text){
        Label label = (Label) scene.lookup("#statusBar");
        label.setText(text);
    }
    public void update(){
        char[][] gameMatrix = game.getGameLogic().getGameMatrix();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b = (Button) scene.lookup("#b" + i + j);
                if(gameMatrix[i][j] == 'X'){
                    b.setText(String.valueOf(game.getUserSymbol()));
                    b.setTextFill(Color.web(game.getUserColor()));
                }else if(gameMatrix[i][j] == 'O'){
                    b.setText(String.valueOf(game.getAiSymbol()));
                    b.setTextFill(Color.web(game.getAiColor()));
                }else{
                    b.setText("");
                }
            }
        }
    }

    public void gameEnded(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game ended");
        alert.setHeaderText("Do you want to start a new game?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        ButtonType quit = new ButtonType("Quit");
        alert.getButtonTypes().setAll(yes, no, quit);
        alert.showAndWait();
        if(alert.getResult().getText().equals("Yes")){
            game.newGame();
            updateStatusBar("New Game started. It's your turn.");
        }
        if(alert.getResult().getText().equals("Quit")){
            quit(null);
        }
    }

    @Override
    public void userWin() {
        game.gamesOver();
        updateStatusBar(LocalizationHelper.getString("info.youWin"));
        gameEnded();
    }

    @Override
    public void aiWin() {
        game.gamesOver();
        updateStatusBar(LocalizationHelper.getString("info.aiWin"));
        gameEnded();
    }

    @Override
    public void wrongCell() {
        updateStatusBar(LocalizationHelper.getString("info.wrongCell"));
    }

    @Override
    public void allCellOccupied() {
        game.gamesOver();
        updateStatusBar(LocalizationHelper.getString("info.draw"));
        gameEnded();
    }

    public void saveSettings(){
        game.saveSettings();
    }
}
