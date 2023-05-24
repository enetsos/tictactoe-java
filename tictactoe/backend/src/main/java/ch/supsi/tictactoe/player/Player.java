package ch.supsi.tictactoe.player;

public abstract class Player {

    protected char symbol;

    protected String color;

    protected char[][] gameMatrix;

    public Player(char symbol, String color, char[][] gameMatrix) {
        this.symbol = symbol;
        this.color = color;
        this.gameMatrix = gameMatrix;
    }

    public Player(char symbol, char[][] gameMatrix) {
        this(symbol, "black", gameMatrix);
    }


    public char getSymbol() {
        return symbol;
    }


    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public void setGameMatrix(char[][] gameMatrix){
        this.gameMatrix = gameMatrix;
    }

    public String getColor() {
        return color;
    }
}
