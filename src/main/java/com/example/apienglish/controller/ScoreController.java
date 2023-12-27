package com.example.apienglish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apienglish.entity.Account;
import com.example.apienglish.entity.Level;
import com.example.apienglish.entity.Score;
import com.example.apienglish.entity.Unit;
import com.example.apienglish.exception.ResourceNotFoundException;
import com.example.apienglish.repository.AccountRepository;
import com.example.apienglish.repository.LevelRepository;
import com.example.apienglish.repository.ScoreRepository;
import com.example.apienglish.repository.UnitRepository;
import com.example.apienglish.service.ScoreService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ScoreController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private LevelRepository levelRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	@Autowired
	private ScoreService scoreService;

	@PostMapping("levels/{levelId}/units/{unitId}/accounts/{accountId}/saveScores") // Updated path variable name here
	public ResponseEntity<Score> saveScore(@RequestBody Score score, @PathVariable Long accountId,
			@PathVariable Long unitId, @PathVariable Long levelId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
		score.setAccount(account);
		
		Unit unit = unitRepository.findById(unitId)
				.orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + unitId));
		score.setUnit(unit);
		
		Level level = levelRepository.findById(levelId)
				.orElseThrow(() -> new ResourceNotFoundException("Level not found with id: " + levelId));
		score.setLevel(level);

		Score savedScore = scoreService.saveScore(score);

		return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
	}
	
	@GetMapping("/accounts/{accountId}/getAllScoresByAccountId")
	public ResponseEntity<?> getAllScoresByAccountId(@PathVariable("accountId") Long accountId) {
		return new ResponseEntity<>(scoreRepository.getAllScoresByAccountId(accountId), HttpStatus.OK);
	}
	
	@GetMapping("/accounts/{unitId}/getAllScoresByUnitId")
	public ResponseEntity<?> getAllScoresByUnitId(@PathVariable("unitId") Long unitId) {
		return new ResponseEntity<>(scoreRepository.getAllScoresByUnitId(unitId), HttpStatus.OK);
	}
	
	
}
