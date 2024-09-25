package com.games.crapsgame.helpers;

import com.games.crapsgame.models.Game;

import java.util.HashMap;

public class TestHelpers {

    public static Game createGame(double stake,int rounds){
        Game game = new Game();
        game.setRounds(rounds);
        game.setType("CRAPS");
        game.setStake(stake);
        game.setOutcome(new HashMap<>());
        game.setRollsAndTotals(new HashMap<>());
        return game;
    }

    public static Game createGame(){
        Game game = new Game();
        game.setRounds(1);
        game.setType("CRAPS");
        game.setStake(100);
        game.setOutcome(new HashMap<>());
        game.setRollsAndTotals(new HashMap<>());
        return game;
    }
}
