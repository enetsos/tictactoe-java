package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameLogicListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameLogic{
    private Player[] players;

    private boolean gamesOver = false;

    private char[][] gameMatrix;

    private List<GameLogicListener> listeners = new ArrayList<>();

    public GameLogic(GameSettings gameSettings) {
        gameMatrix = new char[3][3];
        this.players = new Player[2];
        this.players[0] = new User(gameSettings.getUserChar(), gameMatrix);
        this.players[1] = new Ai(gameSettings.getAiChar(), gameMatrix);
    }

    public void newGame(){
        gameMatrix = new char[3][3];
        players[0].setGameMatrix(gameMatrix);
        players[1].setGameMatrix(gameMatrix);
        gamesOver = false;
    }


    public void addListener(GameLogicListener listener){
        listeners.add(listener);
    }

    public void setGameMatrix(char[][] gameMatrix){
        this.gameMatrix = gameMatrix;
        for(int i = 0; i < players.length; i++){
            players[i].setGameMatrix(gameMatrix);
        }
    }

    public void setUserSymbol(char symbol){
        players[0].setSymbol(symbol);
    }

    public void setAiSymbol(char symbol){
        players[1].setSymbol(symbol);
    }

    public void setUserColor(String color){
        players[0].setColor(color);
    }

    public void setAiColor(String color){
        players[1].setColor(color);
    }

    public void setGamesOver(){
        this.gamesOver = true;
    }

    public char[][] getGameMatrix() {
        return gameMatrix;
    }

    public void playerAction(int x, int y){
        if(gamesOver){
            return;
        }
        if(((User)players[0]).play(x, y)) {
            if(checkWin(players[0].getSymbol())){
                for (GameLogicListener l: listeners) {
                    l.userWin();
                }
                return;
            }
            if(((Ai)players[1]).play()){
                if(checkWin(players[1].getSymbol())){
                    for (GameLogicListener l: listeners) {
                        l.aiWin();
                    }
                }
                //Entrambi hanno potuto giocare ma nessuno ha ancora vinto
            }else{
                for (GameLogicListener l: listeners) {
                    l.allCellOccupied();
                }
            }
        }else{
            System.out.println("Cella sbagliato");
            for (GameLogicListener l: listeners) {
                l.wrongCell();
            }

        }
    }

    private boolean checkWin(char symbol){
        int countRow = 0;
        int countCol = 0;

        // Check rows and columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] == symbol){
                    countRow++;
                }
                if(gameMatrix[j][i] == symbol){
                    countCol++;
                }
            }

            if(countRow == 3 || countCol == 3)
                return true;

            countRow = 0;
            countCol = 0;
        }

        //int count = 0;
        //Check diagonals
        for(int i = 0; i < 3; i++){
            if(gameMatrix[i][i] == symbol){
                countRow++;
            }
            if(gameMatrix[i][2-i] == symbol){
                countCol++;
            }
        }
        if(countRow == 3 || countCol == 3)
            return true;

        return false;
    }

    public String getColor(char gameMatrix) {
        for(int i = 0; i < players.length; i++){
            if(players[i].getSymbol() == gameMatrix){
                return players[i].getColor();
            }
        }
        return null;
    }

    public String getUserColor() {
        return players[0].getColor();
    }

    public String getAiColor() {
        return players[1].getColor();
    }

    public char getUserSymbol() {
        return players[0].getSymbol();
    }

    public char getAiSymbol() {
        return players[1].getSymbol();
    }
}
