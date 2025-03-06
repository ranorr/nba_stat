package com.skyhawk.nbastat.data;

import com.skyhawk.nbastat.model.GameStatistics;

import java.io.Serializable;

public class PlayerAggregateStatistics implements Serializable {

    private Long playerId;
    private String playerName;
    private double averagePoints;
    private double averageRebounds;
    private double averageAssists;
    private double averageSteals;
    private double averageBlocks;
    private double averageFouls;
    private double averageTurnovers;
    private double averageMinutesPlayed;
    private int gamesPlayed;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getAverageMinutesPlayed() {
        return averageMinutesPlayed;
    }

    public double getAverageTurnovers() {
        return averageTurnovers;
    }

    public double getAverageFouls() {
        return averageFouls;
    }

    public double getAverageBlocks() {
        return averageBlocks;
    }

    public double getAverageSteals() {
        return averageSteals;
    }

    public double getAverageAssists() {
        return averageAssists;
    }

    public double getAverageRebounds() {
        return averageRebounds;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public void setAverageRebounds(double averageRebounds) {
        this.averageRebounds = averageRebounds;
    }

    public void setAverageAssists(double averageAssists) {
        this.averageAssists = averageAssists;
    }

    public void setAverageSteals(double averageSteals) {
        this.averageSteals = averageSteals;
    }

    public void setAverageBlocks(double averageBlocks) {
        this.averageBlocks = averageBlocks;
    }

    public void setAverageFouls(double averageFouls) {
        this.averageFouls = averageFouls;
    }

    public void setAverageTurnovers(double averageTurnovers) {
        this.averageTurnovers = averageTurnovers;
    }

    public void setAverageMinutesPlayed(double averageMinutesPlayed) {
        this.averageMinutesPlayed = averageMinutesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void updateStatistics(GameStatistics statistics) {
        this.averagePoints = updateAverage(this.averagePoints, statistics.getPoints());
        this.averageRebounds = updateAverage(this.averageRebounds, statistics.getRebounds());
        this.averageAssists = updateAverage(this.averageAssists, statistics.getAssists());
        this.averageSteals = updateAverage(this.averageSteals, statistics.getSteals());
        this.averageBlocks = updateAverage(this.averageBlocks, statistics.getBlocks());
        this.averageFouls = updateAverage(this.averageFouls, statistics.getFouls());
        this.averageTurnovers = updateAverage(this.averageTurnovers, statistics.getTurnovers());
        this.averageMinutesPlayed = updateAverage(this.averageMinutesPlayed, statistics.getMinutesPlayed());
        this.gamesPlayed = this.gamesPlayed + 1;
    }

    private double updateAverage(double average, double newValue) {
        return (average * gamesPlayed + newValue) / (gamesPlayed + 1);
    }
}
