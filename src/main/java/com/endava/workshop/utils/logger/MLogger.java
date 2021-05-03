package com.endava.workshop.utils.logger;

import org.apache.log4j.Logger;

/**
 * Class for registering events in linear sequence
 */
public class MLogger {

    static Logger MLogger = Logger.getLogger(MLogger.class.getName());

    public MLogger(){
        MLogger.info("Initializing Log4j Parameters");
    }

    /**
     * Method to register an informative event in Tracking file
     * @param message
     */
    public static void info(String message) {
        MLogger.info(message);
    }

    /**
     * Method to register an Warning event in Tracking file
     * @param message
     */
    public static void warn(String message) {
        MLogger.warn(message);
    }

    /**
     * Method to register an Error event in Tracking file
     * @param message
     */
    public static void error(String message) {
        MLogger.error(message);
    }

    /**
     * Method to register an Fatal event in Tracking file
     * @param message
     */
    public static void fatal(String message) {
        MLogger.fatal(message);
    }

    /**
     * Method to register a debugging file in Tracking file
     * @param message
     */
    public static void debug(String message) {
        MLogger.debug(message);
    }
}
