package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.About;
import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

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
        alert.setHeaderText("Are you sure you want to start a new game?");
        ButtonType saveAndNewGame = new ButtonType("Save");
        ButtonType newGame = new ButtonType("Don't Save");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(saveAndNewGame, newGame, cancel);
        alert.showAndWait();
        //add save button

        if(alert.getResult().getText().equals("Save")){
            saveGameAs(e);
            game.newGame();
            updateStatusBar("New Game started. It's your turn.");
        }

        if(alert.getResult().getText().equals("Don't Save")){
            game.newGame();
            updateStatusBar("New Game started. It's your turn.");
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
        alert.setTitle("Quit");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            Platform.exit();
        }
    }

    @FXML
    public void editSymbols(ActionEvent e) {
        saveSettings();
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

        updateStatusBar("Your turn");
        String id = button.getId();
        int row = Integer.parseInt(id.substring(1, 2));
        int col = Integer.parseInt(id.substring(2, 3));
        game.getGameLogic().playerAction(row, col);
        update();
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
                b.setText(String.valueOf(gameMatrix[i][j]));
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
        System.out.println("User ha vinto");
        game.gamesOver();
        updateStatusBar("You win! :)");
        gameEnded();
    }

    @Override
    public void aiWin() {
        System.out.println("AI ha vinto");
        game.gamesOver();
        updateStatusBar("You lose! :(");
        gameEnded();
    }

    @Override
    public void wrongCell() {
        System.out.println("Cella già occupata");
        updateStatusBar("Wrong cell! Retry!");
    }

    @Override
    public void allCellOccupied() {
        System.out.println("Pareggio");
        game.gamesOver();
        updateStatusBar("Draw! :|");
        gameEnded();
    }

    public void saveSettings(){
        game.saveSettings();
    }
}
