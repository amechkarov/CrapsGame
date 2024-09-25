package com.games.crapsgame.utils;

import com.games.crapsgame.models.Game;
import com.games.crapsgame.models.dto.GameDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GameMapper {

    public Game fromDto(GameDto gameDto) {
        Game game = new Game();
        game.setType(gameDto.getType());
        game.setRounds(gameDto.getRounds());
        game.setStake(gameDto.getStake());
        game.setOutcome(new HashMap<>());
        game.setRollsAndTotals(new HashMap<>());
        return game;
    }
}
