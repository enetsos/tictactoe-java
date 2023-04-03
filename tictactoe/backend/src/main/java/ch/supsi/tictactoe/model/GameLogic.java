package ch.supsi.tictactoe.model;

public class GameLogic {
    private Player[] players;

    private boolean win;

    private char[][] gameMatrix;

    public GameLogic(GameSettings gameSettings) {
        gameMatrix = new char[3][3];
        this.players = new Player[2];
        this.players[0] = new User(gameSettings.getUserChar(), gameMatrix);
        this.players[1] = new User(gameSettings.getAiChar(), gameMatrix);
    }

    public void startGame(){}

    public char[][] getGameMatrix() {
        return gameMatrix;
    }

    public boolean playerAction(int x, int y){
        return ((User)players[0]).play(x, y);
    }

    private void checkWin(){
    }
}
