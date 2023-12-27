package com.example.apienglish.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.Role;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
	 List<Level> findByLevelNameContaining(String levelName);

	
}