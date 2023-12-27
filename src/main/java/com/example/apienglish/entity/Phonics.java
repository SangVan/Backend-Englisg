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
@Table(name = "phonics")

public class Phonics {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl; // URL on Cloudinary
    private String audioUrl;
    private String mean;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Unit unit;
    
	public Phonics() {
		super();
	}


	


	public Phonics(Long id, String name, String imageUrl, String audioUrl, String mean, Unit unit) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.audioUrl = audioUrl;
		this.mean = mean;
		this.unit = unit;
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


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getAudioUrl() {
		return audioUrl;
	}


	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}


	public String getMean() {
		return mean;
	}


	public void setMean(String mean) {
		this.mean = mean;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
    
}
