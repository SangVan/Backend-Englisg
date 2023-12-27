package com.example.apienglish.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apienglish.entity.Level;
import com.example.apienglish.service.LevelService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/levels")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<Level> getAllLevels() {
        return levelService.getAllLevels();
    }

    @GetMapping("/{id}")
    public Optional<Level> getLevelById(@PathVariable Long id) {
        return levelService.getLevelById(id);
    }

    @PostMapping
    public Level createLevel(@RequestBody Level level) {
        return levelService.createLevel(level);
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable Long id) {
        levelService.deleteLevel(id);
    }
    
 // Cập nhật Level theo id
 	@PutMapping("/editLevel/{levelId}")
 	public ResponseEntity<?> editLevel(@RequestBody Level level, @PathVariable Long levelId) {
 		return new ResponseEntity<>(levelService.updateLevel(level, levelId), HttpStatus.CREATED);
 	}
}



