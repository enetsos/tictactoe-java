package ch.supsi.tictactoe.model;

public class User extends Player {

    private static final String DEFAULT_SYMBOL = "X";
    private static Boolean round = true;

    public User() {
        super(DEFAULT_SYMBOL, User.round);
    }

    @Override
    public Boolean isRound() {
        return User.round;
    }

    @Override
    public void setRound(Boolean round) {
        User.round = round;
    }
}
