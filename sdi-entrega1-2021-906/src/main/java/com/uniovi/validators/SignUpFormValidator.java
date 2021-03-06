package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class SignUpFormValidator implements Validator{
	
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
	
		if(user.getEmail().isEmpty() || user.getName().isEmpty() || user.getSurname().isEmpty() || user.getPassword().isEmpty() || user.getPasswordConfirm().isEmpty()) {
			errors.rejectValue("email", "Error.empty.message");
		}
		if(usersService.getUserByEmail(user.getEmail()) != null)
			errors.rejectValue("email", "Error.signup.email.duplicate.message");
		if(!user.getPasswordConfirm().equals(user.getPassword())) 
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence.message");
	}

}
