package ch.supsi.tictactoe.model;

import java.io.Serializable;

public class SettingsContainer implements Serializable {
    private static final long serialVersionUID = 1L;

    private LanguageModel languageModel;
    private ThemeModel themeModel;

    public SettingsContainer(){
        this.languageModel = new LanguageModel();
        this.themeModel = new ThemeModel();
    }

    public SettingsContainer(LanguageModel languageModel, ThemeModel themeModel){
        this.languageModel = languageModel;
        this.themeModel = themeModel;
    }

    public void setLanguage(String language){
        languageModel.setLanguage(language);
    }

    public String getLanguage(){
        return languageModel.getLanguage();
    }
    public ThemeModel getThemeModel(){
        return themeModel;
    }
}
