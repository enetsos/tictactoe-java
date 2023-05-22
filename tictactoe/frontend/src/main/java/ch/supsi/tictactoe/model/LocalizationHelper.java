package ch.supsi.tictactoe.model;

public class LocalizationHelper {
    public static String getString(String key) {
        LocalizationHandler handler = LocalizationModel.getInstance();
        if(handler.isInitialized()) {
            return handler.localize(key);
        } else {
            return key;
        }
    }
}
