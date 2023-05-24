package ch.supsi.tictactoe.player;

public class User extends Player {

    public static final char DEFAULT_SYMBOL = 'X';

    public User(char[][] gameMatrix) {
        super(DEFAULT_SYMBOL, gameMatrix);
    }

    public User(char symbol, char[][] gameMatrix) {
        super(symbol, gameMatrix);
    }

    public boolean play (int row, int column){
        if(gameMatrix[row][column] != 0){
            return false;
        }
        gameMatrix[row][column] = DEFAULT_SYMBOL;
        return true;
    }
}
