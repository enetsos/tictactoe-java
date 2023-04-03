package ch.supsi.tictactoe.model;

public class Game {
    private GameLogic gameLogic;



    public Game() {
        GameSettings gameSettings = new GameSettings();
        gameLogic = new GameLogic(gameSettings);
    }

    public void playerAction(int x, int y){
        gameLogic.playerAction(x, y);
    }

}
