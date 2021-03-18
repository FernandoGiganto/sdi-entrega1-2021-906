package com.uniovi.services;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
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
	
	public Page<Offer> getOfferExceptingUsersOffer(Pageable pageable,User user){
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.getOfferExceptingUsersOffer(pageable,user);
		return offers;
	}
	
	public Page<Offer> searchByTitleExceptingUsersOffer(Pageable pageable,String searchText,User user){
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText= "%"+searchText+"%";
		offers = offersRepository.searchByTitleExceptingUsersOffer(pageable, searchText, user);
		return offers;
	}

	public void buyOffer(Long id,User comprador) {
		Optional<Offer> offer = offersRepository.findById(id);
		
		Offer o_aux = getOffer(id);
		
		Optional<User> user = usersRepository.findById(comprador.getId());
		if(user.get().getMoney() >= offer.get().getPrice()) {
			offer.get().setComprador(comprador);
			user.get().addOfferBought(o_aux);
			user.get().setMoney((user.get().getMoney()-offer.get().getPrice()));
			usersRepository.save(user.get());
			offersRepository.save(offer.get());
			
		}
		
	}
}
