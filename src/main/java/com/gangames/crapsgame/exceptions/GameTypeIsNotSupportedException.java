package com.gangames.crapsgame.exceptions;

public class GameTypeIsNotSupportedException extends RuntimeException {

    public GameTypeIsNotSupportedException(){
        super("This game type is not supported!");
    }
}
