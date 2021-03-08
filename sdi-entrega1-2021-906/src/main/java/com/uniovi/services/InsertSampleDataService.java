package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user1= new User("pedrodiaz@gmail.com", "Pedro", "Díaz");
		User user2= new User("lucasnuñez@gmail.com", "Lucas", "Núñez");
		User user3= new User("mariarodriguez@gmail.com", "María", "Rodríguez");
		User user4= new User("martaalmonte@gmail.com", "Marta", "Almonte");
		User user5= new User("pelayovaldes@gmail.com", "Pelayo", "Valdes");
		User user6= new User("fernandogiganto@gmial.com", "Fernando", "Giganto");
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}

}
