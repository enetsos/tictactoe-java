package ch.supsi.tictactoe.listener;

public interface GameLogicListener {
    public void userWin();
    public void aiWin();
    public void wrongCell();
    public void allCellOccupied();

}
