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
@Table(name="ansreview")
public class AnsReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String explainRight; 
    private String explainWrong;
   
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lesson lesson;

	public AnsReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnsReview(Long id, String name, String explainRight, String explainWrong, Lesson lesson) {
		super();
		this.id = id;
		this.name = name;
		this.explainRight = explainRight;
		this.explainWrong = explainWrong;
		this.lesson = lesson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplainRight() {
		return explainRight;
	}

	public void setExplainRight(String explainRight) {
		this.explainRight = explainRight;
	}

	public String getExplainWrong() {
		return explainWrong;
	}

	public void setExplainWrong(String explainWrong) {
		this.explainWrong = explainWrong;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}
