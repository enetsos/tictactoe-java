package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameListener;

import java.util.ArrayList;

public class GameLogic {
    private Player[] players;

    private boolean win;

    private char[][] gameMatrix;

    public GameLogic(GameSettings gameSettings) {
        gameMatrix = new char[3][3];
        this.players = new Player[2];
        this.players[0] = new User(gameSettings.getUserChar(), gameMatrix);
        this.players[1] = new Ai(gameSettings.getAiChar(), gameMatrix);
    }

    public void setGameMatrix(char[][] gameMatrix){
        this.gameMatrix = gameMatrix;
        for(int i = 0; i < players.length; i++){
            players[i].setGameMatrix(gameMatrix);
        }
    }

    public void startGame(){}

    public char[][] getGameMatrix() {
        return gameMatrix;
    }

    public boolean playerAction(int x, int y){
        if(((User)players[0]).play(x, y)) {
            if(((Ai)players[1]).play()){
                return true;
            }else{
                checkWin();
                return false;
            }
        }
        return false;

    }

    private void checkWin(){

    }
}
