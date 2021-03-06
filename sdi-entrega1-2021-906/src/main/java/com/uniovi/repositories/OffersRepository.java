package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer,Long>{
	
	@Query("SELECT o FROM Offer o WHERE  o.user <> ?1 ")
	Page<Offer> getOfferExceptingUsersOffer(Pageable pageable,User user);
	
	@Query("SELECT o FROM Offer o WHERE LOWER(o.title) LIKE LOWER(?1) AND  o.user <> ?2 ")
	Page<Offer> searchByTitleExceptingUsersOffer(Pageable pageable,String searchText,User User);
	
	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT o FROM Offer o WHERE id = ?1")
	Offer findId(Long id);
	


}
