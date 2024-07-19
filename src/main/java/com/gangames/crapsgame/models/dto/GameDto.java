package com.gangames.crapsgame.models.dto;

public class GameDto {

    private String type;

    private double stake;

    private Integer rounds;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }
}
