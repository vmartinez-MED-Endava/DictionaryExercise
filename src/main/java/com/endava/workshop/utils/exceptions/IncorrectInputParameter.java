package com.endava.workshop.utils.exceptions;

public class IncorrectInputParameter extends HandleException{

    public IncorrectInputParameter(Exception e){
        super("User Inserted invalid characters Exception", e.getMessage());
    }
}
