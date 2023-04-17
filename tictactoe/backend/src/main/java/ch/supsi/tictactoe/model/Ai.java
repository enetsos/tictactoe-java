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

        if(!freeSpace()){
            return false;
        }

        while(!played){

            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if(gameMatrix[row][col] == 0){
                gameMatrix[row][col] = symbol;
                return true;
            }
        }
        return true;
    }

    private boolean freeSpace(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }

}
