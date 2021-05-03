package com.endava.workshop.utils.exceptions;

public class IncorrectInputParameter extends HandlerException {

    /**
     * Method to handle the Exception arose after inserting a incorrect value as parameter
     * @param e
     */
    public IncorrectInputParameter(Exception e){
        super("User Inserted invalid characters Exception", e.getMessage());
    }
}
