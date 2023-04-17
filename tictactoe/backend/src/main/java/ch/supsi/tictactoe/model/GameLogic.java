package ch.supsi.tictactoe.model;

public class GameLogic {
    private Player[] players;

    private boolean win;

    private char[][] gameMatrix;


    public GameLogic(GameSettings gameSettings) {
        gameMatrix = new char[3][3];
        this.players = new Player[2];
        this.players[0] = new User(gameSettings.getUserChar(), gameMatrix);
        this.players[1] = new Ai(gameSettings.getAiChar(), gameMatrix);
    }

    public void startGame(){}

    public char[][] getGameMatrix() {
        return gameMatrix;
    }

    public boolean playerAction(int x, int y){
        if(((User)players[0]).play(x, y)) {
            if(((Ai)players[1]).play()){
                checkWin();
                return false;
            }else{
                return true;
            }
        }
        return false;

    }


    private void checkWin(){

    }
}
