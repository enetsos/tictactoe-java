package ch.supsi.tictactoe.model;

public abstract class Player {

    private char symbol;

    private boolean round;

    private char[][] gameMatrix;

    public Player(char symbol, char[][] gameMatrix) {
        this.symbol = symbol;
        this.gameMatrix = gameMatrix;
    }

    public Player(char[][] gameMatrix) {
        this.gameMatrix = gameMatrix;
    }

    public char getSymbol() {
        return symbol;
    }

}
