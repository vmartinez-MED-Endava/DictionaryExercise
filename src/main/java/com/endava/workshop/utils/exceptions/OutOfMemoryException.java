package com.endava.workshop.utils.exceptions;

public class OutOfMemoryException extends HandlerException {

    /**
     * Customized Exception for warning whether the resources of memory were overpassed by the
     * computing processing. Instead of blocking the system availability, this Exception would be thrown
     * to stop the processing and thus avoid the overload
     */
    public OutOfMemoryException() {
        super("Input parameters produced an unmanageable amount of data");
    }
}
