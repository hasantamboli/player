package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.PlayerNotFoundException;
import com.model.Player;
import com.service.PlayerService;

@RestController
@RequestMapping("/player")
@CrossOrigin( origins="http://localhost:4200")
public class PlayerController {

	@Autowired
	PlayerService playerService;
	
	
	@PostMapping("/addPlayer")
	public ResponseEntity<Player> savePlayer(@RequestBody Player p){
		
		Player player=playerService.addPlayer(p);
		return ResponseEntity.status(HttpStatus.CREATED).header("Add", "Player Added").body(player);
	}
	
	@GetMapping("/getPlayer/{playerId}")
	public ResponseEntity<Player> getPlayer(@PathVariable("playerId")int playerId)
	{
		Player player=playerService.getPlayer(playerId);
		return ResponseEntity.status(HttpStatus.FOUND).header("Get", "Player Get").body(player);
	}
	
	@GetMapping("/getAllPlayers")
	public List<Player> getAllPlayer(){
		return playerService.getAllPlayer();
	}
	
	@DeleteMapping("/deletePlayer/{playerId}")
	public ResponseEntity<Map<String,Object>>deletePlayer(@PathVariable("playerId")int playerId)throws PlayerNotFoundException
	{
		Map<String,Object>response= playerService.deletePlayer(playerId);
		return ResponseEntity.ok(response);	
	}
	
	@PutMapping("/updatePlayer")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player)
	{
		Player p=playerService.updatePlayer(player);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Updated", "Player Updated").body(player);
	}
	
}