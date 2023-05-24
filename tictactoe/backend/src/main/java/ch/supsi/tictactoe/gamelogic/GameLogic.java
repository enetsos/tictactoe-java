package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.player.Ai;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.player.User;

public class GameLogic{
    private Player[] players;

    private char[][] gameMatrix;

    private String language;

    private String currentLanguage;


    private GameLogicListener listener;


    //=========== CONSTRUCTOR ===========//
    public GameLogic(){
        gameMatrix = new char[3][3];
        this.players = new Player[2];
    }

    //=========== GETTERS ===========//
    public char getUserSymbol(){
        return players[0].getSymbol();
    }
    public char getAiSymbol(){
        return players[1].getSymbol();
    }
    public String getLanguage(){
        return language;
    }
    public String getCurrentLanguage() { return currentLanguage;}
    public char[][] getGameMatrix() {
        return gameMatrix;
    }
    public String getUserColor() {
        return players[0].getColor();
    }

    public String getAiColor() {
        return players[1].getColor();
    }

    //=========== SETTERS ===========//
    public void setListener(GameLogicListener listener){
        this.listener = listener;
    }
    public void setGameMatrix(char[][] gameMatrix){
        this.gameMatrix = gameMatrix;
        for(int i = 0; i < players.length; i++){
            players[i].setGameMatrix(gameMatrix);
        }
    }
    public void setLanguage(String language){
        if(this.language == null)
            this.currentLanguage = language;
        this.language = language;
    }
    public void setAiSymbol(char aiChar){
        if(players[1] == null){
            players[1] = new Ai(aiChar, gameMatrix);
        }else{
            players[1].setSymbol(aiChar);
        }

    }
    public void setUserSymbol(char userChar){
        if(players[0] == null){
            players[0] = new User(userChar, gameMatrix);
        }else {
            players[0].setSymbol(userChar);
        }

    }
    public void setUserColor(String color) {
        players[0].setColor(color);
    }

    public void setAiColor(String color) {
        players[1].setColor(color);
    }


    //=========== PRIVATE METHODS ===========//
    private boolean userWin(){
        return checkWin(User.DEFAULT_SYMBOL);
    }

    private boolean AIWin(){
        return checkWin(Ai.DEFAULT_SYMBOL);
    }
    private boolean isDraw(){
        for(int i = 0; i < gameMatrix.length; i++){
            for(int j = 0; j < gameMatrix[i].length; j++){
                if(gameMatrix[i][j] == '\u0000'){
                    return false;
                }
            }
        }
        return true;
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

    //=========== PUBLIC METHODS ===========//
    public void newGame(){
        gameMatrix = new char[3][3];
        players[0].setGameMatrix(gameMatrix);
        players[1].setGameMatrix(gameMatrix);
    }
    public boolean playerAction(int x, int y){

        if(((User)players[0]).play(x, y)) {
            if (userWin()){
                listener.userWin();
            }else{
                ((Ai) players[1]).play();
                if(AIWin()){
                    listener.aiWin();
                }else if(isDraw()){
                    listener.allCellOccupied();
                }
            }
        }else {
            return false;
        }
        return true;
    }
}
