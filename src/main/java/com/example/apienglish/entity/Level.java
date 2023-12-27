package com.example.apienglish.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "levels")
public class Level {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long levelId;
	 	
	 	@Column(name = "name")
	    private String levelName;
	    
	  

		public Level() {
			super();
		}

		public Level(String levelName) {
			super();
			this.levelName = levelName;
		}

		public Long getLevelId() {
			return levelId;
		}

		public void setLevelId(Long levelId) {
			this.levelId = levelId;
		}

		public String getLevelName() {
			return levelName;
		}

		public void setLevelName(String levelName) {
			this.levelName = levelName;
		}
}
