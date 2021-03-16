package com.uniovi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Offer {

	@Id 
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private LocalDate discharge_date;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "comprador_id")
	private User comprador;
	
	public Offer(String title,String description,double price,LocalDate date) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.discharge_date=date;
	}
	
	public Offer(String title,String description,double price,LocalDate date,User user) {
		super();
		this.setTitle(title);
		this.setDescription(description);
		this.setPrice(price);
		this.setUser(user);
		this.setDischarge_date(date);
		this.comprador = null;
	}
	
	public Offer() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(LocalDate discharge_date) {
		this.discharge_date = discharge_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", description=" + description + ", discharge_date="
				+ discharge_date + ", price=" + price + "]";
	}
	
	
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getComprador() {
		return comprador;
	}

	public void setComprador(User comprador) {
		this.comprador = comprador;
	}
	
	
}
