package ch.supsi.tictactoe.model;

public class User extends Player {

    public static final char DEFAULT_SYMBOL = 'X';

    public User(char[][] gameMatrix) {
        super(DEFAULT_SYMBOL, gameMatrix);
    }

    public User(char symbol, char[][] gameMatrix) {
        super(symbol, gameMatrix);
    }

    public boolean play (int row, int column){return true;}
}
