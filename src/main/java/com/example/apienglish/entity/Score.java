package com.example.apienglish.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "score")

public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scoreId;

	private float score;

	private String completionPeriod;

	private String numberCorrect;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "unit_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Unit unit;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "level_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Level level;

	public Score() {
		super();
	}

//	public Score(Long scoreId, float score, String completionPeriod, String numberCorrect, Account account) {
//		super();
//		this.scoreId = scoreId;
//		this.score = score;
//		this.completionPeriod = completionPeriod;
//		this.numberCorrect = numberCorrect;
//		this.account = account;
//	}
	
	public Score(Long scoreId, float score, String completionPeriod, String numberCorrect, Account account, Unit unit,
			Level level) {
		super();
		this.scoreId = scoreId;
		this.score = score;
		this.completionPeriod = completionPeriod;
		this.numberCorrect = numberCorrect;
		this.account = account;
		this.unit = unit;
		this.level = level;
	}

	public Long getScoreId() {
		return scoreId;
	}	

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getCompletionPeriod() {
		return completionPeriod;
	}

	public void setCompletionPeriod(String completionPeriod) {
		this.completionPeriod = completionPeriod;
	}

	public String getNumberCorrect() {
		return numberCorrect;
	}

	public void setNumberCorrect(String numberCorrect) {
		this.numberCorrect = numberCorrect;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}