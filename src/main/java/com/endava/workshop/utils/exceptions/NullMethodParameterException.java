package com.endava.workshop.utils.exceptions;

public class NullMethodParameterException extends HandlerException{

    /**
     *  Customized Exception thrown whether the user passed a null object instead of
     *  the required parameter of the specified method. Under the hood, a NullPointerException would arise normally
     *  Exception preferred as it is method-scope
     */
    public NullMethodParameterException(){
        super("Invalid Input Parameter - Null object was passed as parameter");
    }
}
