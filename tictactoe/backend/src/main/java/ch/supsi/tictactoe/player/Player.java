package ch.supsi.tictactoe.player;

import java.io.Serializable;

public abstract class Player implements Serializable, Playable {
    private static final long serialVersionUID = 1L;
    protected Player[][] gameMatrix;
    protected PlayerType playerType;

    public Player(Player[][] gameMatrix){
        this.gameMatrix = gameMatrix;
    };
    public void setGameMatrix(Player[][] gameMatrix){
        this.gameMatrix = gameMatrix;
    }
    public PlayerType getPlayerType(){
        return playerType;
    }

}
