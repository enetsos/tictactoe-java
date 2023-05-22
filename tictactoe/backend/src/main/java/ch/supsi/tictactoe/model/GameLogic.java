package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameLogicListener;

import java.util.ArrayList;
import java.util.List;

public class GameLogic{
    private Player[] players;
    private boolean gamesOver = false;

    private char[][] gameMatrix;

    private String language;


    private List<GameLogicListener> listeners = new ArrayList<>();


    public GameLogic(){
        gameMatrix = new char[3][3];
        this.players = new Player[2];
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

    public void setLanguage(String language){
        this.language = language;
    }
    public void setAIChar(char aiChar){
        if(players[1] == null){
            players[1] = new Ai(aiChar, gameMatrix);
        }else{
            players[1].setSymbol(aiChar);
        }
    }
    public void setUserChar(char userChar){
        if(players[0] == null){
            players[0] = new User(userChar, gameMatrix);
        }else {
            players[0].setSymbol(userChar);
        }
    }

    public char getUserChar(){
        return players[0].getSymbol();
    }
    public char getAiChar(){
        return players[1].getSymbol();
    }

    public String getLanguage(){
        return language;
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
}
