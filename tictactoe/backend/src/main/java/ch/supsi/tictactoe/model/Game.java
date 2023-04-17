package ch.supsi.tictactoe.model;

import java.io.File;

public class Game {
    private GameLogic gameLogic;



    public Game() {
        GameSettings gameSettings = new GameSettings();
        gameLogic = new GameLogic(gameSettings);
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public boolean saveGame(File file){
        return GameSaver.save(file, gameLogic);
    }

}
