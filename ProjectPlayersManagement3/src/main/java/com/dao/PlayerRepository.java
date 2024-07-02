package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Player;
@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

	
}
