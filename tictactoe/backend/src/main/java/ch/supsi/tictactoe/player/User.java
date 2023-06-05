package ch.supsi.tictactoe.player;

import java.io.Serializable;

public class User extends Player implements Serializable {

    public User(Player[][] gameMatrix){
        super(gameMatrix);
        this.playerType = PlayerType.USER;
    };


    public boolean play (int row, int column){
        if(gameMatrix[row][column] != null){
            return false;
        }
        gameMatrix[row][column] = this;
        return true;
    }

    public boolean isUser(){
        return true;
    }
}
