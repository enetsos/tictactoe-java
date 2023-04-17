package ch.supsi.tictactoe.model;

public class Ai extends Player{
    public static final char DEFAULT_SYMBOL = 'O';

    public Ai(char[][] gameMatrix) {
        super(DEFAULT_SYMBOL, gameMatrix);
    }

    public Ai(char symbol, char[][] gameMatrix) {
        super(symbol, gameMatrix);
    }

    public boolean play (){
        boolean played = false;
        while(!played){

            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if(gameMatrix[row][col] == 0){
                System.out.println("AIAction: " + row + " " + col);
                gameMatrix[row][col] = symbol;
                return true;
            }
        }
        return true;
    }

}
