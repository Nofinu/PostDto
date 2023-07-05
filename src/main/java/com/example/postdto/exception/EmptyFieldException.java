package com.example.postdto.exception;

public class EmptyFieldException extends Exception{
    public EmptyFieldException() {
        super("Empty Field");
    }
}
