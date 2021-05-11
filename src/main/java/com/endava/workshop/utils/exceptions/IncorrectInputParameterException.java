package com.endava.workshop.utils.exceptions;

public class IncorrectInputParameterException extends HandlerException {

    /**
     * Customized Exception for alarming whether the user typed a forbidden character
     * Forbidden characters refers to Special characters defined along Regular Expressions filters
     * The List of forbidden characters contains the following items \.[]{}()<>*+-=!?^$|
     */
    public IncorrectInputParameterException(){
        super("User Inserted invalid characters Exception");
    }
}
