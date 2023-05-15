package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.About;
import ch.supsi.tictactoe.AppFx;
import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.model.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

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
    }

    @FXML
    public void editLanguage(ActionEvent e) {
        MenuItem mi = (MenuItem) e.getSource();
        System.out.println(mi.getId());
        //AppFx.switchLanguage(new Locale("it", "CH"));
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

        String id = button.getId();
        int row = Integer.parseInt(id.substring(1, 2));
        int col = Integer.parseInt(id.substring(2, 3));
        game.getGameLogic().playerAction(row, col);
        update();
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

    @Override
    public void userWin() {
        System.out.println("User ha vinto");
        game.gamesOver();
    }

    @Override
    public void aiWin() {
        System.out.println("AI ha vinto");
        game.gamesOver();
    }

    @Override
    public void wrongCell() {
        System.out.println("Cella già occupata");
    }

    @Override
    public void allCellOccupied() {
        System.out.println("Pareggio");
        game.gamesOver();
    }
}
