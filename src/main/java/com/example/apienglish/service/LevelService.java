package com.example.apienglish.service;



import java.util.List;
import java.util.Optional;

import com.example.apienglish.entity.Level;

public interface LevelService {
    List<Level> getAllLevels();
    Optional<Level> getLevelById(Long levelId);
    Level createLevel(Level level);
    void deleteLevel(Long levelId);
    Level updateLevel(Level updatedLevel, Long levelId);
}

