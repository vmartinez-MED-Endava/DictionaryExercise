package com.endava.workshop.utils.exceptions;

public class EmptyInputParameterException extends HandlerException {

    /**
     * Method to handle the Exception arose after inserting an Empty value as parameter
     * @param e
     */
    public EmptyInputParameterException(Exception e){
        super(" User forgot to introduce a valid input parameter ", e.getMessage());
    }
}
