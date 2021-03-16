package com.uniovi.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferFormValidator;

@Controller
public class OffersController {
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AddOfferFormValidator addOfferFormValidator;
	
	
	@RequestMapping("/offer/list")
	public String getListado(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());
		

		
		return"offer/list";
	}

	
	@RequestMapping(value = "/offer/add", method = RequestMethod.GET)
	public String setOffer(Model model) {
		model.addAttribute("offer",new Offer());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@Validated Offer offer,BindingResult result) {
		addOfferFormValidator.validate(offer, result);
		if(result.hasErrors())
			return "/offer/add";
		offer.setDischarge_date(LocalDate.now());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
	
		offer.setUser(activeUser);
		offersService.addOffer(offer);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/buy/{id}")
	public String buyOffer(@PathVariable Long id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		
		offersService.buyOffer(id,activeUser);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/offer/boughtList")
	public String getListadoCompra(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		
		model.addAttribute("offersList", activeUser.getOffersBought());
		
		
		
		return"offer/boughtList";
	}
}
