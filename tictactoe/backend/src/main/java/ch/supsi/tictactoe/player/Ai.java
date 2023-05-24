package ch.supsi.tictactoe.player;

import ch.supsi.tictactoe.listener.GameLogicListener;

import java.util.ArrayList;
import java.util.List;

public class Ai extends Player {
    public static final char DEFAULT_SYMBOL = 'O';

    private final List<GameLogicListener> listeners = new ArrayList<>();

    private int nextRow = -1;
    private int nextCol = -1;

    public Ai(char[][] gameMatrix) {
        super(DEFAULT_SYMBOL, gameMatrix);
    }

    public Ai(char symbol, char[][] gameMatrix) {
        super(symbol, gameMatrix);
    }

    public void addListener(GameLogicListener listener){
        listeners.add(listener);
    }

    public boolean play (){

        int freeCells = countFreeCells();

        if(freeCells == 0){
            return false;
        }

        if(checkWin(Ai.DEFAULT_SYMBOL)){
            gameMatrix[nextRow][nextCol] = DEFAULT_SYMBOL;
        } else if(checkWin(User.DEFAULT_SYMBOL)){
            gameMatrix[nextRow][nextCol] = DEFAULT_SYMBOL;
        }else{
            nextRow = (int) (Math.random() * 3);
            nextCol = (int) (Math.random() * 3);
            while(gameMatrix[nextRow][nextCol] != 0){
                nextRow = (int) (Math.random() * 3);
                nextCol = (int) (Math.random() * 3);
            }
            gameMatrix[nextRow][nextCol] = DEFAULT_SYMBOL;
        }

        return true;
    }

    private int countFreeCells(){
        int count = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    private boolean checkWin(){
        return checkWin(symbol);
    }

    private boolean checkWin(char symbol){
        int countRow = 0;
        int countCol = 0;

        // Check rows and columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] == symbol){
                    countRow++;
                }
                if(gameMatrix[j][i] == symbol){
                    countCol++;
                }
            }


            if(countRow == 2){
                for(int j = 0; j < 3; j++){
                    if(gameMatrix[i][j] == 0){
                        nextRow = i;
                        nextCol = j;
                        return true;
                    }
                }
            }
            if(countCol == 2){
                for(int j = 0; j < 3; j++){
                    if(gameMatrix[j][i] == 0){
                        nextRow = j;
                        nextCol = i;
                        return true;
                    }
                }
            }
            countRow = 0;
            countCol = 0;
        }

        //int count = 0;
        //Check diagonals
        for(int i = 0; i < 3; i++){
            if(gameMatrix[i][i] == symbol){
                countRow++;
            }
            if(gameMatrix[i][2-i] == symbol){
                countCol++;
            }
        }
        if(countRow == 2){
            for(int i = 0; i < 3; i++){
                if(gameMatrix[i][i] == 0){
                    nextRow = i;
                    nextCol = i;
                    return true;
                }
            }
        }
        if(countCol == 2){
            for(int i = 0; i < 3; i++){
                if(gameMatrix[i][2-i] == 0){
                    nextRow = i;
                    nextCol = 2-i;
                    return true;
                }
            }
        }

        return false;
    }

}
