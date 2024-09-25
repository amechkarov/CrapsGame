package com.games.crapsgame.exceptions;

public class GameTypeIsNotSupportedException extends RuntimeException {

    public GameTypeIsNotSupportedException(){
        super("This game type is not supported!");
    }
}
