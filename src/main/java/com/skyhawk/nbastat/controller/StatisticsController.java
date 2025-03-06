package com.skyhawk.nbastat.controller;

import com.skyhawk.nbastat.controller.DTO.StatisticDTO;
import com.skyhawk.nbastat.data.PlayerAggregateStatistics;
import com.skyhawk.nbastat.data.TeamAggregateStatistics;
import com.skyhawk.nbastat.model.GameStatistics;
import com.skyhawk.nbastat.model.Player;
import com.skyhawk.nbastat.service.PlayerService;
import com.skyhawk.nbastat.service.StatisticsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/log")
    public ResponseEntity<Void> logStatistics(@RequestBody List<StatisticDTO> gameStatistics) {
        gameStatistics.forEach(s -> {
            Player player = playerService.getPlayer(s.playerId());
            if(player == null) {
                throw new EntityNotFoundException("Player with Id " + s.playerId() + " isn't exist");
            }
            GameStatistics statistics = new GameStatistics(player, s.gameDate(), s.points(), s.rebounds(), s.assists(), s.steals(), s.blocks(), s.fouls(), s.turnovers(), s.minutesPlayed());
            statisticsService.logStatistics(statistics);
        } );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<PlayerAggregateStatistics> getPlayerStats(@PathVariable Long playerId) {
        PlayerAggregateStatistics playerAggregateStatistics = statisticsService.getPlayerAggregateStatistics(playerId);
        if(playerAggregateStatistics == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerAggregateStatistics);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<TeamAggregateStatistics> getTeamStats(@PathVariable Long teamId) {
        TeamAggregateStatistics teamAggregateStatistics = statisticsService.getTeamAggregateStatistics(teamId);
        if(teamAggregateStatistics == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamAggregateStatistics);
    }
}
