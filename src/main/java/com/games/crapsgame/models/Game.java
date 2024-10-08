package com.games.crapsgame.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.games.crapsgame.exceptions.GameTypeIsNotSupportedException;
import com.games.crapsgame.exceptions.StakeOutOfBoundsException;
import com.games.crapsgame.exceptions.UnexpectedRoundsException;
import com.games.crapsgame.models.enums.GameTypes;
import com.games.crapsgame.utils.EnumValuesValidator;
import com.games.crapsgame.utils.Formatters;

import java.util.Map;

public class Game {

    private GameTypes type;

    private double stake;

    @JsonIgnore
    private Integer rounds;

    private Map<String,Number> outcome;

    private Map<Integer,Integer> rollsAndTotals;

    public GameTypes getType() {
        return type;
    }

    public void setType(String type) {
        if(!EnumValuesValidator.isValidEnum(GameTypes.class, type)){
            throw new GameTypeIsNotSupportedException();
        }
        this.type = GameTypes.valueOf(type);
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        if(stake<=0 || stake > Integer.MAX_VALUE || Formatters.hasMoreDecimalPlaces(stake,2)){
            throw new StakeOutOfBoundsException();
        }
        this.stake = Formatters.roundToTwoDecimalPlaces(stake);
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        if (rounds==null){
            rounds = 1;
        }else if(rounds<1){
            throw new UnexpectedRoundsException();
        }
        this.rounds = rounds;
    }

    public Map<String,Number> getOutcome() {
        return outcome;
    }

    public void setOutcome(Map<String,Number> outcome) {
        this.outcome = outcome;
    }

    public Map<Integer, Integer> getRollsAndTotals() {
        return rollsAndTotals;
    }

    public void setRollsAndTotals(Map<Integer, Integer> rollsAndTotals) {
        this.rollsAndTotals = rollsAndTotals;
    }
}
