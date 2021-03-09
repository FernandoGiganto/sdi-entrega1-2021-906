package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	private String surname;
	private String role;
	
//	private String password;
//	@Transient // propiedad que no se almacena e la tabla.
//	private String passwordConfirm;
	
	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
	private Set<Offer> offers;
	
	public User(String email,String name,String surname) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	
	public User() {};
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getPasswordConfirm() {
//		return passwordConfirm;
//	}
//
//	public void setPasswordConfirm(String passwordConfirm) {
//		this.passwordConfirm = passwordConfirm;
//	}
	
	public String getFullName() {
		return this.name + " " + this.surname;
	}
	
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Offer> getOffers() {
		return offers;
	}
}