package com.skyhawk.nbastat.repository;

import com.skyhawk.nbastat.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
