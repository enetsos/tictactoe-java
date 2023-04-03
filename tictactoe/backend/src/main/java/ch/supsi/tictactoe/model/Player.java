package ch.supsi.tictactoe.model;

public abstract class Player {

    private String symbol;

    private Boolean round;

    public Player(String symbol, Boolean round) {
        this.symbol = symbol;
        this.round = round;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public abstract Boolean isRound();

    public abstract void setRound(Boolean round);

}
