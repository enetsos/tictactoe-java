package ch.supsi.tictactoe.controller;


import ch.supsi.tictactoe.model.LocalizationHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationController {

    private LocalizationHandler localizationHandler;

    // public constructor
    // - why do we have LocalizationHandler as a parameter here in this constructor?
    // - since LocalizationHandler is not used anywhere else, so why don't we just instantiate it inside this class?
    // - and why do we declare a LocalizationHandler interface, when we pass an instance of a LocalizationModel class when we use it?
    public LocalizationController(LocalizationHandler handler) {
        // we should check handler is a valid reference
        // and handle any exceptions

        this.localizationHandler = handler;
    }

    public String getBundleName() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getBundleName();
        }

        // should handle exception
        return null;
    }

    public Locale getLocale() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getLocale();
        }

        // should handle exception
        return null;
    }

    public ResourceBundle getResourceBundle() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getResourceBundle();
        }

        // should handle exception
        return null;
    }

    public String localize(String key) {
        if (key == null || key.isEmpty()) {
            // should handle exception
            return "";
        }

        if (localizationHandler.isInitialized()) {
            return localizationHandler.localize(key);
        }

        return key;
    }

}
