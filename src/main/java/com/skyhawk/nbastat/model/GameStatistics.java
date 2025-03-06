package com.skyhawk.nbastat.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "game_statistics")
public class GameStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(nullable = false)
    private LocalDate gameDate;

    @Column(nullable = false)
    private int points;

    @Column(nullable = false)
    private int rebounds;

    @Column(nullable = false)
    private int assists;

    @Column(nullable = false)
    private int steals;

    @Column(nullable = false)
    private int blocks;

    @Column(nullable = false)
    private int fouls;

    @Column(nullable = false)
    private int turnovers;

    @Column(nullable = false)
    private float minutesPlayed;

    public GameStatistics() {
    }

    public GameStatistics(Player player, LocalDate gameDate, int points, int rebounds, int assists, int steals, int blocks, int fouls, int turnovers, float minutesPlayed) {
        this.player = player;
        this.gameDate = gameDate;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.fouls = fouls;
        this.turnovers = turnovers;
        this.minutesPlayed = minutesPlayed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public float getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(float minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
