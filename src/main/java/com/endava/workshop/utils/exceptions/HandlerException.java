package com.endava.workshop.utils.exceptions;
import org.apache.log4j.Logger;

/**
 * General class for handling all kind of customized exceptions
 * Following this parent-child pattern permits a better maintainability
 */
public class HandlerException extends Exception {
        private String code;

    /**
     * Saves Exception code and Logs the Exception message in Tracking file
     * @param code
     * @param message
     */
    public HandlerException(String code, String message) {
        this.setCode(code);
        Logger.getLogger("Logger").error(code + message);
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
        Logger.getLogger("Logger").error(code + message);
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
