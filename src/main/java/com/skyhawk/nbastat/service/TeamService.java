package com.skyhawk.nbastat.service;

import com.skyhawk.nbastat.model.Team;
import com.skyhawk.nbastat.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + id));
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team updateTeam(Long id, Team teamDetails) {
        Team team = getTeam(id);
        team.setName(teamDetails.getName());
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        Team team = getTeam(id);
        teamRepository.delete(team);
    }
}
