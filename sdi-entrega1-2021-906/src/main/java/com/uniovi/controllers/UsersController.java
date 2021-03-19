package com.uniovi.controllers;

import java.util.LinkedList;
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
import org.springframework.web.context.request.ServletWebRequest;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;

	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String getListado(Model model){
		model.addAttribute("usersList", usersService.getUsers());
		return"user/list";
	}
	

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET )
	public String delete(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST )
	public String delete(ServletWebRequest request){
		if(request.getParameterValues("idChk") != null){
	        for(String id : request.getParameterValues("idChk")){
	        	usersService.deleteUser(Long.valueOf(id));
	            } 
	    }
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/update" )
	public String update(Model model){
		 
		model.addAttribute("userList",usersService.getUsers());
		return"redirect:/user/list";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		
		signUpFormValidator.validate(user, result);
		if(result.hasErrors())
			return "signup";
		user.setMoney(100.0);
		user.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		
		return "redirect:home";
			
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,String error ) {
		if(error != null )
			model.addAttribute("error", "El usuario o la contraseña no existen");
	
		model.addAttribute("user", new User());
		
		return "login";
	}
	
	

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model,Pageable pageable,@RequestParam(value="",required=false)String searchText) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());
		
		Page<Offer> ofertas= new PageImpl<Offer>(new LinkedList<Offer>());
		
		if(searchText != null && !searchText.isEmpty()) {
			ofertas = offersService.searchByTitleExceptingUsersOffer(pageable, searchText, activeUser);
		}else {
			ofertas = offersService.getOfferExceptingUsersOffer(pageable,activeUser);

		}
		
		
		model.addAttribute("offersList", ofertas.getContent());
		model.addAttribute("page", ofertas);
		model.addAttribute("activeUser", activeUser);
		
		return "home";
	}

}
