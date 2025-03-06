package com.skyhawk.nbastat.repository;

import com.skyhawk.nbastat.model.GameStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStatisticsRepository extends JpaRepository<GameStatistics, Long> {

    List<GameStatistics> findByPlayerId(Long playerId);
    List<GameStatistics> findByPlayerTeamId(Long teamId);
}
