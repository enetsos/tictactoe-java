package ch.supsi.tictactoe.model;

public abstract class Player {

    protected char symbol;

    private boolean round;

    protected char[][] gameMatrix;

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

    public void setGameMatrix(char[][] gameMatrix){
        this.gameMatrix = gameMatrix;
    }

}
