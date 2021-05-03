package com.endava.workshop.utils.exceptions;

public class EmptyInputParameterException extends HandleException{

    public EmptyInputParameterException(Exception e){
        super(" User forgot to introduce a valid input parameter ", e.getMessage());
    }
}
