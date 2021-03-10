package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer,Long>{
	
	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1))")
	List<Offer> searchByTitle(String searchText);

}
