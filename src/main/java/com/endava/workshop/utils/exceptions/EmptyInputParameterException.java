package com.endava.workshop.utils.exceptions;

public class EmptyInputParameterException extends HandlerException {

    /**
     * Method to handle the Exception arose after inserting an Empty value as parameter
     */
    public EmptyInputParameterException(){
        super(" User forgot to introduce a valid input parameter");
    }
}
