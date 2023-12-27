//package com.example.apienglish.entity;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "answers")
//public class AnswerEx {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long answerId;
//	
//	private String imageUrl;
//	
//    private String audioUrl;
//    
//	private String answerText;	
//
//    private String description;
//    
//    private String correctAnswers;
//    
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "exercise_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Exercise exercise;
//
//	public AnswerEx() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public AnswerEx(Long answerId, String imageUrl, String audioUrl, String answerText, String description,
//			String correctAnswers, Exercise exercise) {
//		super();
//		this.answerId = answerId;
//		this.imageUrl = imageUrl;
//		this.audioUrl = audioUrl;
//		this.answerText = answerText;
//		this.description = description;
//		this.correctAnswers = correctAnswers;
//		this.exercise = exercise;
//	}
//
//	public Long getAnswerId() {
//		return answerId;
//	}
//
//	public void setAnswerId(Long answerId) {
//		this.answerId = answerId;
//	}
//
//	public String getImageUrl() {
//		return imageUrl;
//	}
//
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
//
//	public String getAudioUrl() {
//		return audioUrl;
//	}
//
//	public void setAudioUrl(String audioUrl) {
//		this.audioUrl = audioUrl;
//	}
//
//	public String getAnswerText() {
//		return answerText;
//	}
//
//	public void setAnswerText(String answerText) {
//		this.answerText = answerText;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getCorrectAnswers() {
//		return correctAnswers;
//	}
//
//	public void setCorrectAnswers(String correctAnswers) {
//		this.correctAnswers = correctAnswers;
//	}
//
//	public Exercise getExercise() {
//		return exercise;
//	}
//
//	public void setExercise(Exercise exercise) {
//		this.exercise = exercise;
//	}
//    
//    
//	
//}
