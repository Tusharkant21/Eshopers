package com.example.springjpa.exceptions;

public class AuthenticationFailedException  extends  IllegalArgumentException{
    public AuthenticationFailedException(String msg){
        super(msg);
    }

}
