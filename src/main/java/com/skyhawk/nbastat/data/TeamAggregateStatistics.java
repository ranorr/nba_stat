package com.skyhawk.nbastat.data;

import com.skyhawk.nbastat.model.GameStatistics;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeamAggregateStatistics implements Serializable {

    private Long teamId;
    private String teamName;
    private double averagePoints;
    private double averageRebounds;
    private double averageAssists;
    private double averageSteals;
    private double averageBlocks;
    private double averageFouls;
    private double averageTurnovers;
    private double averageMinutesPlayed;
    private int gamesPlayed;
    private int totalPlayers;

    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public double getAverageRebounds() {
        return averageRebounds;
    }

    public double getAverageAssists() {
        return averageAssists;
    }

    public double getAverageSteals() {
        return averageSteals;
    }

    public double getAverageBlocks() {
        return averageBlocks;
    }

    public double getAverageFouls() {
        return averageFouls;
    }

    public double getAverageTurnovers() {
        return averageTurnovers;
    }

    public double getAverageMinutesPlayed() {
        return averageMinutesPlayed;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public void updateStats(List<GameStatistics> teamGameStatistics) {
        this.averagePoints = calculateTeamAverage(teamGameStatistics, GameStatistics::getPoints);
        this.averageRebounds = calculateTeamAverage(teamGameStatistics, GameStatistics::getRebounds);
        this.averageAssists = calculateTeamAverage(teamGameStatistics, GameStatistics::getAssists);
        this.averageSteals = calculateTeamAverage(teamGameStatistics, GameStatistics::getSteals);
        this.averageBlocks = calculateTeamAverage(teamGameStatistics, GameStatistics::getBlocks);
        this.averageFouls = calculateTeamAverage(teamGameStatistics, GameStatistics::getFouls);
        this.averageTurnovers = calculateTeamAverage(teamGameStatistics, GameStatistics::getTurnovers);
        this.averageMinutesPlayed = calculateTeamAverage(teamGameStatistics, GameStatistics::getMinutesPlayed);
        this.gamesPlayed = this.gamesPlayed + 1;
        this.totalPlayers = teamGameStatistics.stream().map(stat -> stat.getPlayer().getId()).collect(Collectors.toSet()).size();
    }

    private double calculateTeamAverage(List<GameStatistics> stats, Function<GameStatistics, Number> getter) {
        return stats.stream()
                .mapToDouble(stat -> getter.apply(stat).doubleValue())
                .average()
                .orElse(0.0);
    }
}
