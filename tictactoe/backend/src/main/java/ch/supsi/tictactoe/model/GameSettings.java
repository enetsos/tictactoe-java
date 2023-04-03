package ch.supsi.tictactoe.model;

public class GameSettings {
    private char userChar = User.DEFAULT_SYMBOL;

    private char aiChar = Ai.DEFAULT_SYMBOL;

    private String language;

    public char getUserChar() {
        return userChar;
    }

    public char getAiChar() {
        return aiChar;
    }

    public String getLanguage() {
        return language;
    }

    public void setUserChar(char userChar) {
        if(userChar == aiChar)
            throw new IllegalArgumentException("User and AI cannot have the same symbol");
        this.userChar = userChar;
    }

    public void setAiChar(char aiChar) {
        if(aiChar == userChar)
            throw new IllegalArgumentException("User and AI cannot have the same symbol");
        this.aiChar = aiChar;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
