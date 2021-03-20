package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;

@Component
public class AddOfferFormValidator implements Validator{
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Offer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty.message");
		if(offer.getPrice() < 0)
			errors.rejectValue("price", "Error.add.price.message");
		
	}

}
