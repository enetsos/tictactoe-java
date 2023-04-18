package ch.supsi.tictactoe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameSaver {

    public static boolean save(File file, GameLogic logic){

        StringBuilder builder = new StringBuilder();
        char[][] matrix = logic.getGameMatrix();
        char c;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0)
                    builder.append(' ');
                else
                    builder.append(matrix[i][j]);
            }
            builder.append(System.lineSeparator());
        }

        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(builder);
            writer.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public static boolean load(File file, GameLogic logic){
        try {
            Scanner input = new Scanner(file);
            char[][] matrix = new char[3][3];

            while(input.hasNext()){
                for (int i = 0; i < matrix.length; i++) {
                    String line = input.nextLine();
                    for (int j = 0; j < matrix[i].length; j++) {
                        if(line.charAt(j) == ' ')
                            matrix[i][j] = 0;
                        else
                            matrix[i][j] = line.charAt(j);
                    }
                }
            }
            logic.setGameMatrix(matrix);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
}
