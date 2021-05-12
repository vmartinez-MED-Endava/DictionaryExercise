package com.endava.workshop.utils.exceptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * General class for handling all kind of customized exceptions
 * Following this parent-child pattern permits a better maintainability
 */
public class HandlerException extends Exception {

    private static Logger logger = LogManager.getLogger(HandlerException.class);

    /**
     * Saves Logs messages in Tracking file
     * This Constructor validates if there is an actual LogManager instantiated for tracking the exception message
     *
     * @param message (String): Customized Exception message - any String describing the exception cause.
     */
    public HandlerException(String message) {
        logger.info(message);
    }

    /**
     * Method to store Exception information as Exception message and the Throwable cause
     * @param message (String) : Any message Exception-related
     * @param cause (Throwable) : Error and Exception superclass
     */
    public HandlerException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message);
    }
}
