package com.example.apienglish.service;

import com.example.apienglish.entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getAllScores();
    Score getScoreById(Long scoreId);
    Score saveScore(Score score);
    void deleteScore(Long scoreId);
    
}