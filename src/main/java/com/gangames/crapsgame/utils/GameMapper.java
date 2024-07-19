package com.gangames.crapsgame.utils;

import com.gangames.crapsgame.exceptions.GameTypeIsNotSupportedException;
import com.gangames.crapsgame.models.Game;
import com.gangames.crapsgame.models.dto.GameDto;
import com.gangames.crapsgame.models.enums.GameTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
