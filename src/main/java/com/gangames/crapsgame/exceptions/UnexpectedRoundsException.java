package com.gangames.crapsgame.exceptions;

public class UnexpectedRoundsException extends RuntimeException{

    public UnexpectedRoundsException(){
        super("Rounds can not be negative number!");
    }
}
