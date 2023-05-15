package ch.supsi.tictactoe.model;

import java.util.Locale;
import java.util.ResourceBundle;

public interface LocalizationHandler extends Handler {

    void init(String bundleName, Locale locale);

    String getBundleName();

    Locale getLocale();

    ResourceBundle getResourceBundle();

    String localize(String key);

}
