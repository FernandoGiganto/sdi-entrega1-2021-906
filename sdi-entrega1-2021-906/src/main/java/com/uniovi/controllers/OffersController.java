package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

@Controller
public class OffersController {
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/offer/list")
	public String getListado(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());
		return"offer/list";
	}

	
	@RequestMapping(value = "/offer/add", method = RequestMethod.GET)
	public String setMark(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Offer offer) {

		offersService.addOffer(offer);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/buy/{id}")
	public String buyOffer(@PathVariable Long id) {
		return "redirect:/offer/bought";
	}
}
