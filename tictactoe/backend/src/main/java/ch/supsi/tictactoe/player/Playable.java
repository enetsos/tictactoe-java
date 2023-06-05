package ch.supsi.tictactoe.player;

public interface Playable {
    public default boolean isUser(){
        return false;
    }

    public default boolean isAI(){
        return false;
    }
}
