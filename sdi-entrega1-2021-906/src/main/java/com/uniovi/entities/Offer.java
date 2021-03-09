package com.uniovi.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offer {

	@Id 
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private String discharge_date;
	private double price;
	
	public Offer(String title,String description,double price) {
		super();
		this.title = title;
		this.description = description;
		this.discharge_date = getFechaActual();
		this.price = price;
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
	public String getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(String discharge_date) {
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
	
	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    return formateador.format(ahora);
	}
	
	
}
