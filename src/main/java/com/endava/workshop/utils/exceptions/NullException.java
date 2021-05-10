package com.endava.workshop.utils.exceptions;

public class NullException  extends HandlerException{

    public NullException(){
        super("Invalid Input Parameter - Null object was passed as parameter");
    }
}
