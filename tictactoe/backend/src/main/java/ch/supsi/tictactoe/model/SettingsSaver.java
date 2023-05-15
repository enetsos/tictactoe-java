package ch.supsi.tictactoe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsSaver extends Saver{

    @Override
    public boolean save(File file, GameLogic logic) {
        StringBuilder builder = new StringBuilder();

        builder.append("Settings!");
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

    @Override
    public boolean load(File file, GameLogic logic) {
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
}
