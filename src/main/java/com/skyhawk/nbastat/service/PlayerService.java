package com.skyhawk.nbastat.service;

import com.skyhawk.nbastat.model.Player;
import com.skyhawk.nbastat.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + id));
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player updatePlayer(Long id, Player playerDetails) {
        Player player = getPlayer(id);
        player.setName(playerDetails.getName());
        player.setTeam(playerDetails.getTeam());
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        Player player = getPlayer(id);
        playerRepository.delete(player);
    }


}
