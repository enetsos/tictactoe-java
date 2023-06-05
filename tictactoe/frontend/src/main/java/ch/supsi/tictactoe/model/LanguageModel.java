package ch.supsi.tictactoe.model;

import java.io.Serializable;

public class LanguageModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String language = "en-EN";

    public LanguageModel(String language){
        this.language = language;
    }

    public LanguageModel(){
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }
}
