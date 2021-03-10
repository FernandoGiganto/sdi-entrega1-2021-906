package com.uniovi.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public String home(Model model,Pageable pageable,@RequestParam(value="",required=false)String searchText) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());
		
//		Page<Offer> total = offersService.getOffers(pageable);
//		Set<Offer> propios =  activeUser.getOffers();
		
		Page<Offer> ofertas= new PageImpl<Offer>(new LinkedList<Offer>());
		
		if(searchText != null && !searchText.isEmpty()) {
			ofertas = offersService.searchByTitleExceptingUsersOffer(pageable, searchText, activeUser);
		}else {
			ofertas = offersService.getOfferExceptingUsersOffer(pageable,activeUser);

		}
		
		
		model.addAttribute("offersList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		return "home";
	}

}
