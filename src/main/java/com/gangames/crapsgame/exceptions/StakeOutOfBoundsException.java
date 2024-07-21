package com.gangames.crapsgame.exceptions;

public class StakeOutOfBoundsException extends RuntimeException{

    public StakeOutOfBoundsException(){
        super(String.format("Stake can not be less than %d and more than %d",1,Integer.MAX_VALUE));
    }
}
