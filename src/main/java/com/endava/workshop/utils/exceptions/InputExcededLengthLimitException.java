package com.endava.workshop.utils.exceptions;

public class InputExcededLengthLimitException extends HandlerException{

    public InputExcededLengthLimitException(){
        super("Input String Excedeed the 26-Characters limit - Wrong Input");
    }
}
