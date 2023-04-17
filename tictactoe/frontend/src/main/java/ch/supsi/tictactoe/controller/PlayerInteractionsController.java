package ch.supsi.tictactoe.controller;

import ch.supsi.tictactoe.About;
import ch.supsi.tictactoe.model.Ai;
import ch.supsi.tictactoe.model.Game;
import ch.supsi.tictactoe.model.Player;
import ch.supsi.tictactoe.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class PlayerInteractionsController {

    private Game game;
    private Scene scene;

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
    }

    @FXML
    public void saveGame(ActionEvent e) {
    }

    @FXML
    public void saveGameAs(ActionEvent e) {
    }

    @FXML
    public void quit(ActionEvent e) {
    }

    @FXML
    public void editSymbols(ActionEvent e) {
    }

    @FXML
    public void editLanguage(ActionEvent e) {
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
        if(game.getGameLogic().playerAction(row, col)) {
            update();
        }else{
            System.out.println("Error: cell already occupied");
        }
    }

    private void update(){
        char[][] gameMatrix = game.getGameLogic().getGameMatrix();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b = (Button) scene.lookup("#b" + i + j);
                b.setText(String.valueOf(gameMatrix[i][j]));
            }
        }


    }
}
