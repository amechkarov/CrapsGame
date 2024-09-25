package com.games.crapsgame.controller;

import com.games.crapsgame.exceptions.GameTypeIsNotSupportedException;
import com.games.crapsgame.exceptions.StakeOutOfBoundsException;
import com.games.crapsgame.models.dto.GameDto;
import com.games.crapsgame.service.contracts.GameService;
import com.games.crapsgame.utils.GameMapper;
import com.games.crapsgame.exceptions.UnexpectedRoundsException;
import com.games.crapsgame.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/play")
    public Game playGame(@RequestBody GameDto gameDto){
        try {
            Game game = gameMapper.fromDto(gameDto);
            return gameService.play(game);
        }catch (GameTypeIsNotSupportedException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (StakeOutOfBoundsException | UnexpectedRoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
