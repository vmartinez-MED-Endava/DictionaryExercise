package com.endava.workshop.utils.exceptions;

public class EmptyInputParameterException extends HandlerException {

    /**
     * Customized Exception for alarming whether the user typed an empty string as parameter instead
     * of a valid string.
     *
     */
    public EmptyInputParameterException(){
        super(" User forgot to introduce a valid input parameter - Empty String Exception");
    }
}
