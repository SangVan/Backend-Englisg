package com.example.apienglish.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "account",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  
  private String fullname;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  private String avatarUrl; 	
	private String gender;
	private String birthday;
	private String phoneNumber;
	private String address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "account_roles", 
             joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

   
  @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "account_levels", 
				joinColumns = @JoinColumn(name = "account_id"), 
				inverseJoinColumns = @JoinColumn(name = "level_id"))
	  private Set<Level> levels = new HashSet<>();
  
  @Column(name = "verification_code", length = 64)
  private String verificationCode;
   
  private boolean enabled;
  
  public Account() {
  }

  public Account(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }



public Account(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		String fullname, @NotBlank @Size(max = 120) String password, String avatarUrl, String gender, String birthday,
		String phoneNumber, String address, Set<Role> roles, Set<Level> levels, String verificationCode,
		boolean enabled) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.fullname = fullname;
	this.password = password;
	this.avatarUrl = avatarUrl;
	this.gender = gender;
	this.birthday = birthday;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.roles = roles;
	this.levels = levels;
	this.verificationCode = verificationCode;
	this.enabled = enabled;
}
  
  

public Long getId() {
	return id;
}



public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAvatarUrl() {
	return avatarUrl;
}

public void setAvatarUrl(String avatarUrl) {
	this.avatarUrl = avatarUrl;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getBirthday() {
	return birthday;
}

public void setBirthday(String birthday) {
	this.birthday = birthday;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

public Set<Level> getLevels() {
	return levels;
}

public void setLevels(Set<Level> levels) {
	this.levels = levels;
}

public String getVerificationCode() {
	return verificationCode;
}

public void setVerificationCode(String verificationCode) {
	this.verificationCode = verificationCode;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getFullname() {
	return fullname;
}

public void setFullname(String fullname) {
	this.fullname = fullname;
}

  
}