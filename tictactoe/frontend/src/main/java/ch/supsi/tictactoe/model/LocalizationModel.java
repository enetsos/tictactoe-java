package ch.supsi.tictactoe.model;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationModel implements LocalizationHandler {
    private static LocalizationModel model;

    private boolean initialized;

    private String bundleName;

    private Locale locale;

    private ResourceBundle translations;

    // protected constructor
    // why do we protect the constructor?
    // why do we force construction through a public static method getInstance()?
    protected LocalizationModel() {
        this.initialized = false;
    }

    // singleton construction method
    // why is this method implemented this way?
    // what do we achieve with it?
    public static LocalizationModel getInstance() {
        if (model == null) {
            model = new LocalizationModel();
        }

        return model;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void init(String bundleName, Locale locale) {
        this.bundleName = bundleName;
        this.locale = locale;
        this.translations = ResourceBundle.getBundle(bundleName, locale);
        this.initialized = true;
    }

    @Override
    public String getBundleName() {
        return this.bundleName;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return this.translations;
    }

    @Override
    public String localize(String key) {
        String translation;

        try {
            translation = translations.getString(key);

        } catch (MissingResourceException e) {
            translation = key;
        }

        return translation;
    }

}
