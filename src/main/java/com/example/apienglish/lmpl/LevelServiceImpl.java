package com.example.apienglish.lmpl;


import com.example.apienglish.entity.Level;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.service.LevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> getLevelById(Long levelId) {
        return levelRepository.findById(levelId);
    }

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void deleteLevel(Long levelId) {
        levelRepository.deleteById(levelId);
    }

	@Override
	public Level updateLevel(Level updatedLevel, Long levelId) {
		Level oldLevel = levelRepository.findById(levelId).get();
		
		oldLevel.setLevelName(updatedLevel.getLevelName());
		return null;
	}
}
