package com.skyhawk.nbastat.controller;

import com.skyhawk.nbastat.controller.DTO.PlayerDTO;
import com.skyhawk.nbastat.model.Player;
import com.skyhawk.nbastat.model.Team;
import com.skyhawk.nbastat.service.PlayerService;
import com.skyhawk.nbastat.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerDTO player) {
        Team team = teamService.getTeam(player.teamId());
        if(team == null) {
            throw new EntityNotFoundException("Team with Id " + player.teamId() + " isn't exist");
        }
        Player createdPlayer = playerService.createPlayer(new Player(player.name(), team));
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Player player = playerService.getPlayer(id);
        return ResponseEntity.ok(player);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Player updatedPlayer = playerService.updatePlayer(id, playerDetails);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }
}
