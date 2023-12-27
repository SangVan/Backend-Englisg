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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;
	
	private String lessonNumber;
    private String name;
    private String imageUrl; // URL on Cloudinary    
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "unit_id", referencedColumnName = "unitId")
//    private Unit unit;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Unit unit;
    
	

	public Lesson(Long lessonId, String lessonNumber, String name, String imageUrl, Unit unit) {
		super();
		this.lessonId = lessonId;
		this.lessonNumber = lessonNumber;
		this.name = name;
		this.imageUrl = imageUrl;
		this.unit = unit;
	}


	public Lesson() {
		super();
	}
	
	
	public Long getLessonId() { 
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	public String getLessonNumber() {
		return lessonNumber;
	}


	public void setLessonNumber(String lessonNumber) {
		this.lessonNumber = lessonNumber;
	}
    
    
    
}
