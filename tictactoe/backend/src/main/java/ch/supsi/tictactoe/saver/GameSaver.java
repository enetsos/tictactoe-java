package ch.supsi.tictactoe.saver;
import ch.supsi.tictactoe.player.Player;

import java.io.*;

public class GameSaver{

    public static boolean save(File file, Player[][] gameMatrix){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameMatrix);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static Player[][] load(File file, Player[][] gameMatrix){
        Player[][] result = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (Player[][]) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
        return result;
    }
}
