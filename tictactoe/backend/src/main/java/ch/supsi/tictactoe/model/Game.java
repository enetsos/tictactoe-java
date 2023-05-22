package ch.supsi.tictactoe.model;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.listener.GameLogicListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final GameLogic gameLogic;
    private final SettingsSaver SettingsSaver = new SettingsSaver();
    private final GameSaver GameSaver = new GameSaver();

    private final List<GameListener> listeners = new ArrayList<>();


    public Game(GameLogic logic){
        this.gameLogic = logic;
    }

    public void gamesOver(){
        gameLogic.setGamesOver();
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

    public void setUserSymbol(char symbol){
        gameLogic.setUserSymbol(symbol);
    }

    public void setAiSymbol(char symbol){
        gameLogic.setAiSymbol(symbol);
    }

    public void setUserColor(String color){
        gameLogic.setUserColor(color);
    }

    public void setAiColor(String color){
        gameLogic.setAiColor(color);
    }

    public void loadGame(File file){
        boolean res = GameSaver.load(file, gameLogic);
        if(res){
            for(GameListener listener : listeners){
                listener.update();
            }
        }
    }

    public void newGame(){
        gameLogic.newGame();
        for(GameListener listener : listeners){
            listener.update();
        }
    }

    public void saveSettings(){
        SettingsSaver.save(gameLogic);
    }

    public String getColor(char gameMatrix) {
        return gameLogic.getColor(gameMatrix);
    }

    public String getUserColor() {
        return gameLogic.getUserColor();
    }

    public String getAiColor() {
        return gameLogic.getAiColor();
    }

    public char getUserSymbol() {
        return gameLogic.getUserSymbol();
    }

    public char getAiSymbol() {
        return gameLogic.getAiSymbol();
    }
}
