package com.all.sandboxjava.designpatterns.command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void warn(String tag, String message) {
        LOGGER.log(Level.WARNING, tag + ": " + message);
    }

    public static void info(String tag, String message) {
        LOGGER.log(Level.INFO, tag + ": " + message);
    }
}
