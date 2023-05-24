package ch.supsi.tictactoe.listener;

import ch.supsi.tictactoe.gamelogic.GameResult;

public interface GameListener {
    public void update();
    public void gameOver(GameResult result);
}
