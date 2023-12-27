package com.example.apienglish.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "stories")
	public class Stories {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		
		private String storyName;
		private String videoUrl;
	    private String imageUrl; // URL on Cloudinary
	    
	
		@OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "unit_id", referencedColumnName = "unitId")
	    private Unit unit;
	public Stories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Stories(Long id, Unit unit, String storyName, String videoUrl, String imageUrl) {
		super();
		this.id = id;
		this.unit = unit;
		this.storyName = storyName;
		this.videoUrl = videoUrl;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStoryName() {
		return storyName;
	}
	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
	
	
}
