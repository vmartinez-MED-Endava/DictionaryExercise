package com.endava.workshop.utils.exceptions;

public class OutOfMemoryException extends HandleException{

    public OutOfMemoryException(Exception e) {
        super("Input parameters produced an unmanageable amount of data", e.getMessage());
    }
}
