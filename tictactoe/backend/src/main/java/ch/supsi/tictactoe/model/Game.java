package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameLogic gameLogic;

    private List<GameListener> listeners = new ArrayList<>();



    public Game() {
        GameSettings gameSettings = new GameSettings();
        gameLogic = new GameLogic(gameSettings);
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void addListener(GameListener listener){
        listeners.add(listener);
    }

    public boolean saveGame(File file){
        return GameSaver.save(file, gameLogic);
    }

    public boolean loadGame(File file){
        boolean res = GameSaver.load(file, gameLogic);
        if(res){
            for(GameListener listener : listeners){
                listener.update();
            }
            return true;
        }
        return false;
    }

}
