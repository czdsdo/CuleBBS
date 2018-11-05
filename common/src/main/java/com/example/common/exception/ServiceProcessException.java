package com.example.common.exception;

public class ServiceProcessException extends  RuntimeException {
    public  ServiceProcessException(String message){super(message);}
    public  ServiceProcessException(String messags,Throwable cause){super(messags,cause);}
}
