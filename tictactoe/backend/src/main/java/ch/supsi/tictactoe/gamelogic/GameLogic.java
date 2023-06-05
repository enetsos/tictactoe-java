package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.player.Ai;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.player.PlayerType;
import ch.supsi.tictactoe.player.User;

public class GameLogic{
    private Player[] players;

    private Player[][] gameMatrix;

    private GameLogicListener listener;


    //=========== CONSTRUCTOR ===========//
    public GameLogic(){
        gameMatrix = new Player[3][3];
        this.players = new Player[2];
        players[0] = new User(gameMatrix);
        players[1] = new Ai(gameMatrix);
    }

    //=========== GETTERS ===========//
    public Player[][] getGameMatrix() {
        return gameMatrix;
    }

    //=========== SETTERS ===========//
    public void setListener(GameLogicListener listener){
        this.listener = listener;
    }
    public void setGameMatrix(Player[][] gameMatrix){
        this.gameMatrix = gameMatrix;
        for(int i = 0; i < players.length; i++){
            players[i].setGameMatrix(gameMatrix);
        }
    }

    //=========== PRIVATE METHODS ===========//
    private boolean userWin(){
        return checkWin(players[0].getPlayerType());
    }

    private boolean AIWin(){
        return checkWin(players[1].getPlayerType());
    }
    private boolean isDraw(){
        for(int i = 0; i < gameMatrix.length; i++){
            for(int j = 0; j < gameMatrix[i].length; j++){
                if(gameMatrix[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkWin(PlayerType p){
        int countRow = 0;
        int countCol = 0;

        // Check rows and columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                if(gameMatrix[i][j] != null){
                    if(gameMatrix[i][j].getPlayerType() == p){
                        countRow++;
                    }
                }

                if(gameMatrix[j][i] != null){
                    if(gameMatrix[j][i].getPlayerType() == p){
                        countCol++;
                    }
                }
            }

            if(countRow == 3 || countCol == 3)
                return true;

            countRow = 0;
            countCol = 0;
        }

        for(int i = 0; i < 3; i++){

            if(gameMatrix[i][i] != null){
                if(gameMatrix[i][i].getPlayerType() == p){
                    countRow++;
                }
            }

            if(gameMatrix[i][2-i] != null){
                if(gameMatrix[i][2-i].getPlayerType() == p){
                    countCol++;
                }
            }

        }
        if(countRow == 3 || countCol == 3)
            return true;

        return false;
    }

    //=========== PUBLIC METHODS ===========//
    public void newGame(){
        gameMatrix = new Player[3][3];
        players[0].setGameMatrix(gameMatrix);
        players[1].setGameMatrix(gameMatrix);
    }
    public boolean playerAction(int x, int y){

        if(((User)players[0]).play(x, y)) {
            if (userWin()){
                listener.userWin();
            }else{
                ((Ai) players[1]).play((User)players[0]);
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
