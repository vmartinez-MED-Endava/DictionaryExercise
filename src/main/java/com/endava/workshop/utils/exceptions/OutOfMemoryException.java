package com.endava.workshop.utils.exceptions;

public class OutOfMemoryException extends HandlerException {

    /**
     * Method to handle the Exception arose after overpassing the amount of available memory
     * @param e
     */
    public OutOfMemoryException(Exception e) {
        super("Input parameters produced an unmanageable amount of data", e.getMessage());
    }
}
