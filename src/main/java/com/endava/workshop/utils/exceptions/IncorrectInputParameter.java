package com.endava.workshop.utils.exceptions;

public class IncorrectInputParameter extends HandlerException {

    /**
     * Method to handle the Exception arose after inserting a incorrect value as parameter
     */
    public IncorrectInputParameter(){
        super("User Inserted invalid characters Exception");
    }
}
