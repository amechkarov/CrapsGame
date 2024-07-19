package com.gangames.crapsgame.controller;

import com.gangames.crapsgame.exceptions.GameTypeIsNotSupportedException;
import com.gangames.crapsgame.models.Game;
import com.gangames.crapsgame.models.dto.GameDto;
import com.gangames.crapsgame.service.contracts.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gangames.crapsgame.utils.GameMapper;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/craps")
public class GameController {

    private final GameService gameService;

    private final GameMapper gameMapper;

    @Autowired
    public GameController(GameService gameService, GameMapper gameMapper){
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @PostMapping
    public Game playGame(@RequestBody GameDto gameDto){
        try {
            Game game = gameMapper.fromDto(gameDto);
            return gameService.play(game);
        }catch (GameTypeIsNotSupportedException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
