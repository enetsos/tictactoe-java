package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.saver.GameSaver;
import ch.supsi.tictactoe.saver.SettingsSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game implements GameLogicListener{
    private final GameLogic gameLogic;
    private final SettingsSaver SettingsSaver = new SettingsSaver();
    private final GameSaver GameSaver = new GameSaver();
    private GameListener listener;
    private String language;


    public Game(GameLogic logic){
        this.gameLogic = logic;
        gameLogic.setListener(this);
        this.language = logic.getLanguage();
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }
    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        gameLogic.setLanguage(language);
    }

    public void setListener(GameListener listener){
        this.listener = listener;
    }

    //public void addGameLogicListener(GameLogicListener listener){
    //    gameLogic.setListener(listener);
    //}
    public void saveGame(File file){
        GameSaver.save(file, gameLogic);
    }

    public void loadGame(File file){
        boolean res = GameSaver.load(file, gameLogic);
        if(res){
            listener.update();
        }
    }

    public boolean userPlay(int row, int col){
        return gameLogic.playerAction(row, col);
    }

    public void newGame(){
        gameLogic.newGame();
        listener.update();
    }

    public void saveSettings(){
        SettingsSaver.save(gameLogic);
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

    @Override
    public void userWin() {
        listener.update();
        listener.gameOver(GameResult.WIN);
    }

    @Override
    public void aiWin() {
        listener.update();
        listener.gameOver(GameResult.LOSE);
    }

    @Override
    public void wrongCell() {

    }

    @Override
    public void allCellOccupied() {
        listener.update();
        listener.gameOver(GameResult.DRAW);
    }
}
