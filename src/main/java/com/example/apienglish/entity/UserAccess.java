package com.example.apienglish.entity;

import java.util.Date;

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
@Table(name = "userAccess")
public class UserAccess {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    private String accessDate;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "level_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Level level;

	public UserAccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UserAccess(Long accessId, String accessDate, Account account, Level level) {
		super();
		this.accessId = accessId;
		this.accessDate = accessDate;
		this.account = account;
		this.level = level;
	}



	public Long getAccessId() {
		return accessId;
	}

	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}

	

	public String getAccessDate() {
		return accessDate;
	}



	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}



	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}



	public Level getLevel() {
		return level;
	}



	public void setLevel(Level level) {
		this.level = level;
	}   	
}
