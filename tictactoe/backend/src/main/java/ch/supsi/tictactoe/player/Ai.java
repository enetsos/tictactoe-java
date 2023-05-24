package ch.supsi.tictactoe.player;

import java.io.Serializable;

public class Ai extends Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private int nextRow = -1;
    private int nextCol = -1;

    public Ai(Player[][] gameMatrix){
        super(gameMatrix);
        this.playerType = PlayerType.AI;
    }


    public boolean play (User user){

        int freeCells = countFreeCells();

        if(freeCells == 0){
            return false;
        }

        if(checkWin()){
            gameMatrix[nextRow][nextCol] = this;
        } else if(checkWin(user.getPlayerType())){
            gameMatrix[nextRow][nextCol] = this;
        }else{
            nextRow = (int) (Math.random() * 3);
            nextCol = (int) (Math.random() * 3);
            while(gameMatrix[nextRow][nextCol] != null){
                nextRow = (int) (Math.random() * 3);
                nextCol = (int) (Math.random() * 3);
            }
            gameMatrix[nextRow][nextCol] = this;
        }

        return true;
    }

    private int countFreeCells(){
        int count = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] == null){
                    count++;
                }
            }
        }
        return count;
    }

    private boolean checkWin(){
        return checkWin(getPlayerType());
    }

    private boolean checkWin(PlayerType p){
        int countRow = 0;
        int countCol = 0;

        // Check rows and columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameMatrix[i][j] != null){
                    if(gameMatrix[i][j].getPlayerType() == p){
                        countRow++;
                    }
                }
                if(gameMatrix[j][i] != null){
                    if(gameMatrix[j][i].getPlayerType() == p){
                        countCol++;
                    }
                }
            }

            if(countRow == 2){
                for(int j = 0; j < 3; j++){
                    if(gameMatrix[i][j] == null){
                        nextRow = i;
                        nextCol = j;
                        return true;
                    }
                }
            }
            if(countCol == 2){
                for(int j = 0; j < 3; j++){
                    if(gameMatrix[j][i] == null){
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
            if(gameMatrix[i][i] != null && gameMatrix[2-i][i] != null){
                if(gameMatrix[i][i] != null){
                    if(gameMatrix[i][i].getPlayerType() == p){
                        countRow++;
                    }
                }

                if(gameMatrix[2-i][i] != null){
                    if(gameMatrix[2-i][i].getPlayerType() == p){
                        countCol++;
                    }
                }
            }
        }
        if(countRow == 2){
            for(int i = 0; i < 3; i++){
                if(gameMatrix[i][i] == null){
                    nextRow = i;
                    nextCol = i;
                    return true;
                }
            }
        }
        if(countCol == 2){
            for(int i = 0; i < 3; i++){
                if(gameMatrix[i][2-i] == null){
                    nextRow = i;
                    nextCol = 2-i;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isAI(){
        return true;
    }

}
