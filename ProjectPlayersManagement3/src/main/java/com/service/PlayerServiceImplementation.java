package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PlayerRepository;
import com.exception.PlayerNotFoundException;
import com.model.Player;

@Service
public class PlayerServiceImplementation implements PlayerService{

	@Autowired
	PlayerRepository playerRepo;

	//Adding Player
	@Override
	public Player addPlayer(Player player) {
		return playerRepo.save(player);
	}

	//Get Player
	public Player getPlayer(int playerId) {
		return playerRepo.findById(playerId).orElse(null);
	}
	
	//Get All Players
	@Override
	public List<Player> getAllPlayer() {
		return playerRepo.findAll();
	}

	//Delete Player
	@Override
	public Map<String, Object> deletePlayer(int playerId) throws PlayerNotFoundException {

		Map<String,Object> response=new HashMap<String,Object>();
		
		try {
			Player player=playerRepo.findById(playerId).orElseThrow(()->new PlayerNotFoundException(playerId+" "+"Number Player ID Not Found"));
			playerRepo.delete(player);
			response.put("Deleted",Boolean.TRUE);
			
		}catch(PlayerNotFoundException p) {
			response.put("NotDeleted", p.getMessage());
		}
		return response;
	}

	//Update Player
	@Override
	public Player updatePlayer(Player player) {
		
		Player p=playerRepo.findById(player.getPlayerId()).orElse(null);
		
		p.setPlayerName(player.getPlayerName());
		p.setPlayerTeam(player.getPlayerTeam());
		
		return playerRepo.save(p);
	}
}