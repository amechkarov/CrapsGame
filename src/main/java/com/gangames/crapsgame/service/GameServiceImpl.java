package com.gangames.crapsgame.service;

import com.gangames.crapsgame.models.Game;
import com.gangames.crapsgame.service.contracts.GameService;
import com.gangames.crapsgame.utils.randomprovider.RandomProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.gangames.crapsgame.utils.Formatters.roundToTwoDecimalPlaces;

@Service
public class GameServiceImpl implements GameService {

    private final RandomProvider randomProvider;

    private Map<Integer,Integer> rollsAndTotals;

    private int rollIndex;


    @Autowired
    public GameServiceImpl(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    @Override
    public Game play(Game game) {
        Map<String, Number> outcome = game.getOutcome();
        rollsAndTotals = game.getRollsAndTotals();
        double totalWin = calculateTotalWin(game);

        outcome.put("sumOfWins", totalWin);

        if (game.getRounds() > 1) {
            double totalStakes = game.getStake() * game.getRounds();
            double rtp = totalWin / totalStakes;
            outcome.put("sumOfStakes", totalStakes);
            outcome.put("returnToPlayer", roundToTwoDecimalPlaces(rtp));
        }
        rollIndex=0;
        return game;
    }

    private double calculateTotalWin(Game game){
        int rounds = game.getRounds();
        double totalWin = 0;

        while (rounds > 0) {
            int totalRoll = randomProvider.roll();
            boolean playerWon = playerWins(totalRoll);

            if (playerWon && game.getRounds() == 1) {
                totalWin += game.getStake();
            } else if (playerWon && game.getRounds() > 1) {
                totalWin += game.getStake();
            }
            rounds--;
        }

        return roundToTwoDecimalPlaces(totalWin);
    }

    private boolean playerWins(int totalRoll) {
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
