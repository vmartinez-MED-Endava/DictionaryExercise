package com.endava.workshop.utils.exceptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * General class for handling all kind of customized exceptions
 * Following this parent-child pattern permits a better maintainability
 */
public class HandlerException extends Exception {
        private String code;
        private Logger logger;

    /**
     * Saves Exception code and Logs the Exception message in Tracking file
     * @param message
     */
    public HandlerException(String message) {
        if(logger ==null){
            logger = LogManager.getLogger(HandlerException.class);
        }
        logger.info(message);
    }

    /**
     * Method to store Exception information as code, Exception message and the Throwable cause
     * @param code
     * @param message
     * @param cause
     */
    public HandlerException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
        logger.error(code + message);
    }

    /**
     * Method to retrieve Exception code
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to store Exception code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
