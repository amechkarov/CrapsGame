package com.gangames.crapsgame.service;

import com.gangames.crapsgame.models.Game;
import com.gangames.crapsgame.service.contracts.GameService;
import com.gangames.crapsgame.utils.randomprovider.RandomProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

    private final RandomProvider randomProvider;
    private Map<String, Number> outcome;

    private Map<Integer,Integer> rollsAndTotals;

    private int rollIndex;


    @Autowired
    public GameServiceImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public Game play(Game game) {
        int rounds = game.getRounds();
        double totalWin = 0;
        int totalRoll = 0;
        outcome = game.getOutcome();
        rollsAndTotals = game.getRollsAndTotals();

        while (rounds > 0) {
            totalRoll = randomProvider.roll();
            boolean playerWon = playerWin(totalRoll);
            if (playerWon && game.getRounds() == 1) {
                totalWin += game.getStake();
            } else if (playerWon && game.getRounds() > 1) {
                totalWin += game.getStake();
            }
            rounds--;
        }
        outcome.put("sumOfWins", totalWin);

        if (game.getRounds() > 1) {
            double totalStakes = game.getStake() * game.getRounds();
            double rtp = totalWin / totalStakes;
            outcome.put("sumOfStakes", totalStakes);
            outcome.put("returnToPlayer", rtp);
        }
        rollIndex=0;
        return game;
    }

    private boolean playerWin(int totalRoll) {
        rollIndex++;
        rollsAndTotals.put(rollIndex,totalRoll);
        if (totalRoll == 7 || totalRoll == 11) {
            return true;
        } else if (totalRoll == 2 || totalRoll == 3 || totalRoll == 12) {
            return false;
        } else {
            return playerPointIsHit(totalRoll);
        }
    }

    private boolean playerPointIsHit(int point) {
        while (true) {
            int totalRoll = randomProvider.roll();
            rollIndex++;
            rollsAndTotals.put(rollIndex,totalRoll);
            if (totalRoll == point) {
                return true;
            } else if (totalRoll == 7) {
                return false;
            }
        }
    }
}
