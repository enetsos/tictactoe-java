package ch.supsi.tictactoe.saver;

import ch.supsi.tictactoe.gamelogic.GameLogic;
import ch.supsi.tictactoe.saver.Saver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsSaver extends Saver {

    private static File file;

    public SettingsSaver(File file){
        this.file = file;
    }

    public SettingsSaver(){
        if(file == null)
            setFile();
    }

    @Override
    public boolean save(File file, GameLogic logic) {
        StringBuilder builder = new StringBuilder();

        builder.append("language:").append(logic.getLanguage()).append("\n");
        builder.append("userChar:").append(logic.getUserSymbol()).append("\n");
        builder.append("aiChar:").append(logic.getAiSymbol()).append("\n");
        builder.append("userColor:").append(logic.getUserColor()).append("\n");
        builder.append("aiColor:").append(logic.getAiColor()).append("\n");
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

    public boolean save(GameLogic logic){
        return save(file, logic);
    }

    @Override
    public boolean load(File file, GameLogic logic) {
        try {
            Scanner input = new Scanner(file);
            String lang, user, ai, userColor, aiColor = null;
            try {
                lang = input.nextLine().split(":")[1];
                user = input.nextLine().split(":")[1];
                ai = input.nextLine().split(":")[1];
                userColor = input.nextLine().split(":")[1];
                aiColor = input.nextLine().split(":")[1];
            } catch (Exception e) {
                lang = "en-EN";
                user = "X";
                ai = "O";
                userColor = "black";
                aiColor = "black";
            }

            logic.setLanguage(lang);
            logic.setUserSymbol(user.charAt(0));
            logic.setAiSymbol(ai.charAt(0));
            logic.setUserColor(userColor);
            logic.setAiColor(aiColor);

        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public boolean load(GameLogic logic){
        return load(file, logic);
    }

    private void setFile(){
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();
        String appSettingsDir;

        if (os.contains("win")) {
            appSettingsDir = userHome + File.separator + "tictactoe";
        } else if (os.contains("mac")) {
            appSettingsDir = userHome + File.separator + "Library" + File.separator + "Application Support" + File.separator + "tictactoe";
        } else {
            appSettingsDir = userHome + File.separator + ".tictactoe";
        }

        String appSettingsPath = appSettingsDir + File.separator + "settings.txt";

        File dir = new File(appSettingsDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        this.file = new File(appSettingsPath);
    }
}
