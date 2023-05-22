package ch.supsi.tictactoe.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsSaver extends Saver{

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
        builder.append("userChar:").append(logic.getUserChar()).append("\n");
        builder.append("aiChar:").append(logic.getAiChar()).append("\n");
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
            String lang = null;
            String user = null;
            String ai = null;
            try {
                lang = input.nextLine().split(":")[1];
                user = input.nextLine().split(":")[1];
                ai = input.nextLine().split(":")[1];
            } catch (Exception e) {
                lang = "en-EN";
                user = "X";
                ai = "O";
            }

            logic.setLanguage(lang);
            logic.setUserChar(user.charAt(0));
            logic.setAIChar(ai.charAt(0));

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
