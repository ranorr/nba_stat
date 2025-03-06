package com.skyhawk.nbastat.repository;

import com.skyhawk.nbastat.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
