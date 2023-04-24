package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.listener.GameLogicListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameLogic gameLogic;

    private final List<GameListener> listeners = new ArrayList<>();



    public Game() {
        GameSettings gameSettings = new GameSettings();
        gameLogic = new GameLogic(gameSettings);
    }

    public void gamesOver(){
        gameLogic.gamesOver();
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void addListener(GameListener listener){
        listeners.add(listener);
    }

    public void addGameLogicListener(GameLogicListener listener){
        gameLogic.addListener(listener);
    }
    public void saveGame(File file){
        GameSaver.save(file, gameLogic);
    }

    public void loadGame(File file){
        boolean res = GameSaver.load(file, gameLogic);
        if(res){
            for(GameListener listener : listeners){
                listener.update();
            }
        }
    }

}
