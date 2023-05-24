package ch.supsi.tictactoe.gamelogic;

import ch.supsi.tictactoe.listener.GameListener;
import ch.supsi.tictactoe.listener.GameLogicListener;
import ch.supsi.tictactoe.player.Player;
import ch.supsi.tictactoe.saver.GameSaver;

import java.io.File;

public class Game implements GameLogicListener{
    private final GameLogic gameLogic;
    private final GameSaver GameSaver = new GameSaver();
    private GameListener listener;


    public Game(GameLogic logic){
        this.gameLogic = logic;
        gameLogic.setListener(this);
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }


    public void setListener(GameListener listener){
        this.listener = listener;
    }

    public void saveGame(File file){
        GameSaver.save(file, gameLogic.getGameMatrix());
    }


    public void loadGame(File file){
        System.out.println("Loading game");
        Player[][] tmp = GameSaver.load(file, gameLogic.getGameMatrix());
        if(tmp != null){
            gameLogic.setGameMatrix(tmp);
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
