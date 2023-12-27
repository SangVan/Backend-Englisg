package com.example.apienglish.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "units")
public class Unit {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    private String unitName;
    private String status;
    
    private int time;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "level_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Level level;
    
    
    @OneToOne(mappedBy = "unit")
    private Stories stories;
    
	public Unit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Unit(Long unitId, String unitName, String status, int time, Level level) {
		super();
		this.unitId = unitId;
		this.unitName = unitName;
		this.status = status;
		this.time = time;
		this.level = level;
	}

	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
}

	
