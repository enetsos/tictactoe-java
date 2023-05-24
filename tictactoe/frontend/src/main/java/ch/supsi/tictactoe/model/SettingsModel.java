package ch.supsi.tictactoe.model;

import java.io.*;

public class SettingsModel {

    private SettingsContainer settings;
    private File file;
    private String language;

    public SettingsModel(SettingsContainer settings){
        this.settings = settings;
    }

    public SettingsModel(){
        settings = new SettingsContainer();
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

    public boolean save(SettingsContainer settings){
        this.settings = settings;
        return save();
    }
    public boolean save(){
        if(file == null){
            setFile();
        }
        return save(file);
    }
    public boolean save(File file){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public SettingsContainer load(){
        if(file == null){
            setFile();
        }
        return load(file);
    }

    public SettingsContainer load(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            settings = (SettingsContainer) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
        return settings;
    }

    public void setLanguage(String language){
        settings.setLanguage(language);
    }
    public String getLanguage(){
        if(language == null)
            language = settings.getLanguage();
        return language;
    }
    public ThemeModel getTheme(){
        return settings.getThemeModel();
    }
}
