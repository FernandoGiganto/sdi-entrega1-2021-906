package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	public List<Offer> getOffers() {
		List<Offer> Offers = new ArrayList<Offer>();
		offersRepository.findAll().forEach(Offers::add);
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
}
