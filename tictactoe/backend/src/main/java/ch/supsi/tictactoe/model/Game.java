package ch.supsi.tictactoe.model;

public class Game {
    private GameLogic gameLogic;



    public Game() {
        GameSettings gameSettings = new GameSettings();
        gameLogic = new GameLogic(gameSettings);
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

}
