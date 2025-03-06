package com.skyhawk.nbastat.service;

import com.skyhawk.nbastat.data.PlayerAggregateStatistics;
import com.skyhawk.nbastat.data.TeamAggregateStatistics;
import com.skyhawk.nbastat.model.GameStatistics;
import com.skyhawk.nbastat.repository.GameStatisticsRepository;
import com.skyhawk.nbastat.repository.PlayerRepository;
import com.skyhawk.nbastat.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameStatisticsRepository gameStatisticsRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Transactional
    public void logStatistics(GameStatistics statistics) {
        validateStatistics(statistics);
        gameStatisticsRepository.save(statistics);
        updateAggregateStats(statistics);
    }

    public void validateStatistics(GameStatistics statistics) {

        if(statistics.getFouls() > 6) {
            throw new IllegalArgumentException("Fouls can't exceed from 6");
        }
        if(statistics.getMinutesPlayed() < 0 || statistics.getMinutesPlayed() > 48) {
            throw new IllegalArgumentException("Player can play 0 to 48 minutes");
        }
        if(playerRepository.findById(statistics.getPlayer().getId()).isEmpty()) {
            throw new IllegalArgumentException("User with id " + statistics.getPlayer().getId() + " isn't exist");
        }
        if(teamRepository.findById(statistics.getPlayer().getTeam().getId()).isEmpty()) {
            throw new IllegalArgumentException("User with id " + statistics.getPlayer().getId() + " doesn't have team with id " + statistics.getPlayer().getTeam().getId());
        }

    }

    private void updateAggregateStats(GameStatistics statistics) {
        updatePlayerAggregateStatistics(statistics);
        updateTeamAggregateStatistics(statistics.getPlayer().getTeam().getId());
    }

    private void updatePlayerAggregateStatistics(GameStatistics statistics) {
        String key = "player:" + statistics.getPlayer().getId();
        PlayerAggregateStatistics playerAggregateStatistics = (PlayerAggregateStatistics) redisTemplate.opsForValue().get(key);
        if(playerAggregateStatistics == null) { //Player not exist
            playerAggregateStatistics = new PlayerAggregateStatistics();
            playerAggregateStatistics.setPlayerId(statistics.getPlayer().getId());
            playerAggregateStatistics.setPlayerName(statistics.getPlayer().getName());
        }
        playerAggregateStatistics.updateStatistics(statistics);
        redisTemplate.opsForValue().set(key, playerAggregateStatistics);
    }

    private void updateTeamAggregateStatistics(Long teamId) {
        String key = "team:" + teamId;
        TeamAggregateStatistics teamAggregateStatistics = (TeamAggregateStatistics) redisTemplate.opsForValue().get(key);
        if(teamAggregateStatistics == null) {
            teamAggregateStatistics = new TeamAggregateStatistics();
            teamAggregateStatistics.setTeamId(teamId);
        }
        List<GameStatistics> teamStat = gameStatisticsRepository.findByPlayerTeamId(teamId);
        teamAggregateStatistics.updateStats(teamStat);
        redisTemplate.opsForValue().set(key, teamAggregateStatistics);
    }

    public PlayerAggregateStatistics getPlayerAggregateStatistics(Long playerId) {
        String key = "player:" + playerId;
        return (PlayerAggregateStatistics) redisTemplate.opsForValue().get(key);
    }

    public TeamAggregateStatistics getTeamAggregateStatistics(Long teamId) {
        String key = "team:" + teamId;
        return (TeamAggregateStatistics) redisTemplate.opsForValue().get(key);
    }

}
