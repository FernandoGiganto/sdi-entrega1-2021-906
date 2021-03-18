package com.uniovi.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1= new User("pedrodiaz@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2= new User("lucasnuñez@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3= new User("mariarodriguez@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4= new User("martaalmonte@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5= new User("pelayovaldes@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6= new User("admin@gmail.com", "Fernando", "Giganto",true);
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);
		
		Set user1Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oferta A1","descripcion de la oferta A1",15.0,LocalDate.now(),user1));
				add(new Offer("Oferta A2","descripcion de la oferta A2",5.00,LocalDate.now(),user1));
				add(new Offer("Oferta A3","descripcion de la oferta A3",50.00,LocalDate.now(),user1));
				add(new Offer("Oferta A4","descripcion de la oferta A4",15.99,LocalDate.now(),user1));
			}
		};
		user1.setOffers(user1Offers);
		
		Set user2Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oferta B1","descripcion de la oferta B1",1.00,LocalDate.now(),user2));
				add(new Offer("Oferta B2","descripcion de la oferta B2",55.00,LocalDate.now(),user2));
				add(new Offer("Oferta B3","descripcion de la oferta B3",22.00,LocalDate.now(),user2));
				add(new Offer("Oferta B4","descripcion de la oferta B4",13.50,LocalDate.now(),user2));
			}
		};
		user2.setOffers(user2Offers);
		
		Set user3Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oferta C1","descripcion de la oferta C1",22.22,LocalDate.now(),user3));
				add(new Offer("Oferta C2","descripcion de la oferta C2",52.00,LocalDate.now(),user3));
				add(new Offer("Oferta C3","descripcion de la oferta C3",5.00,LocalDate.now(),user3));
				add(new Offer("Oferta C4","descripcion de la oferta C4",15.00,LocalDate.now(),user3));
			}
		};
		user3.setOffers(user3Offers);
		
		Set user4Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oferta D1","descripcion de la oferta D1",2.0,LocalDate.now(),user4));
				add(new Offer("Oferta D2","descripcion de la oferta D2",53.0,LocalDate.now(),user4));
				add(new Offer("Oferta D3","descripcion de la oferta D3",50.75,LocalDate.now(),user4));
				add(new Offer("Oferta D4","descripcion de la oferta D4",100.00,LocalDate.now(),user4));
			}
		};
		user4.setOffers(user4Offers);
		
		Set user5Offers = new HashSet<Offer>() {
			{
				add(new Offer("Oferta E1","descripcion de la oferta E1",5.10,LocalDate.now(),user5));
				add(new Offer("Oferta E2","descripcion de la oferta E2",12.0,LocalDate.now(),user5));
				add(new Offer("Oferta E3","descripcion de la oferta E3",22.20,LocalDate.now(),user5));
				add(new Offer("Oferta E4","descripcion de la oferta E4",9.99,LocalDate.now(),user5));
			}
		};
		user5.setOffers(user5Offers);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}

}
