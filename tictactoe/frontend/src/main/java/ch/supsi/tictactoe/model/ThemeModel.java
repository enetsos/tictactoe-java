package ch.supsi.tictactoe.model;

import java.io.Serializable;

public class ThemeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final char DEFAULT_SYMBOL_USER = 'X';
    public static final char DEFAULT_SYMBOL_AI = 'O';
    private char userSymbol = DEFAULT_SYMBOL_USER;
    private char aiSymbol = DEFAULT_SYMBOL_AI;
    private String userColor = "black";
    private String aiColor = "black";
    public ThemeModel(char userSymbol, char aiSymbol, String userColor, String aiColor){
        this.userSymbol = userSymbol;
        this.aiSymbol = aiSymbol;
        this.userColor = userColor;
        this.aiColor = aiColor;
    }
    public ThemeModel(){

    }

    public char getUserSymbol() {
        return userSymbol;
    }

    public char getAiSymbol() {
        return aiSymbol;
    }

    public String getUserColor() {
        return userColor;
    }

    public String getAiColor() {
        return aiColor;
    }

    public void setUserSymbol(char userSymbol) {
        this.userSymbol = userSymbol;
    }

    public void setAiSymbol(char aiSymbol) {
        this.aiSymbol = aiSymbol;
    }

    public void setUserColor(String userColor) {
        this.userColor = userColor;
    }

    public void setAiColor(String aiColor) {
        this.aiColor = aiColor;
    }

}
