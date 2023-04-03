package ch.supsi.tictactoe.model;

public class Ai extends Player{
    private static final String DEFAULT_SYMBOL = "O";
    private static  Boolean round = false;

    public Ai() {
        super(DEFAULT_SYMBOL, Ai.round);
    }

    @Override
    public Boolean isRound() {
        return Ai.round;
    }

    @Override
    public void setRound(Boolean round) {
        Ai.round = round;
    }
}
