package com.example.apienglish.lmpl;

import com.example.apienglish.entity.Score;
import com.example.apienglish.repository.ScoreRepository;
import com.example.apienglish.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public Score getScoreById(Long scoreId) {
        Optional<Score> optionalScore = scoreRepository.findById(scoreId);
        return optionalScore.orElse(null);
    }

    @Override
    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public void deleteScore(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }
}