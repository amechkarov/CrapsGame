package com.gangames.crapsgame.exceptions;

public class StakeOutOfBoundsException extends RuntimeException{

    public StakeOutOfBoundsException(){
        super(String.format("Stake can not be less than %.2f, have more than 2 decimal places and be more than %d",0.01,Integer.MAX_VALUE));
    }
}
