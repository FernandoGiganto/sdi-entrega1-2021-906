package com.uniovi.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/user/list")
	public String getListado(Model model){
		model.addAttribute("usersList", usersService.getUsers());
		return"user/list";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model,@RequestParam(value="",required=false)String searchText) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());
		
		List<Offer> total = offersService.getOffers();
		Set<Offer> propios =  activeUser.getOffers();
		
		
		if(searchText != null && !searchText.isEmpty()) {
			List<Offer> aux = offersService.searchOffersByTitle(searchText);
			for(Offer o:propios) {
				aux.remove(o);
			}
			model.addAttribute("offersList", aux);
		}else {
			for(Offer o:propios) {
				total.remove(o);
			}
			model.addAttribute("offersList",total );
		}
		
		
		return "home";
	}

}
