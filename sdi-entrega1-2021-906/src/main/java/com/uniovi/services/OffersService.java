package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> Offers = offersRepository.findAll(pageable);
		return Offers;
	}

	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer Offer) { // Si en Id es null le asignamos el ultimo + 1 de la lista
		offersRepository.save(Offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}
	
	public Page<Offer> searchOffersByTitle(Pageable pageable,String searchText){
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText= "%"+searchText+"%";
		offers = offersRepository.searchByTitle(pageable,searchText);
		return offers;
	}
}
