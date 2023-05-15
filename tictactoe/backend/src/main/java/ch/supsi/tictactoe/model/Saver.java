package ch.supsi.tictactoe.model;

import java.io.File;

public abstract class Saver {
    public abstract boolean save(File file, GameLogic logic);
    public abstract boolean load(File file, GameLogic logic);
}
