package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class LogInFormValidator implements Validator{
	
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty.message");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty.message");
		
		if(usersService.getUserByEmail(user.getEmail()) == null)
			errors.rejectValue("username", "Error.login.email.notfound.message");
		User aux =usersService.getUserByEmail(user.getEmail());
		if(aux.getPassword() != user.getPassword()) 
			errors.rejectValue("password", "Error.login.password.message");
	}

}
