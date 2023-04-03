package ch.supsi.tictactoe.model;

public class Ai extends Player{
    public static final char DEFAULT_SYMBOL = 'O';

    public Ai(char[][] gameMatrix) {
        super(DEFAULT_SYMBOL, gameMatrix);
    }

    public Ai(char symbol, char[][] gameMatrix) {
        super(symbol, gameMatrix);
    }

    public void play (){}

}
