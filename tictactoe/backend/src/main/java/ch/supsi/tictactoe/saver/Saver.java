package ch.supsi.tictactoe.saver;

import ch.supsi.tictactoe.gamelogic.GameLogic;

import java.io.File;

public abstract class Saver {
    public abstract boolean save(File file, GameLogic logic);
    public abstract boolean load(File file, GameLogic logic);
}
