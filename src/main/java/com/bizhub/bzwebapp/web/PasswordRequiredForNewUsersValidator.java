package com.bizhub.bzwebapp.web;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bizhub.bzwebapp.domain.User;

public class PasswordRequiredForNewUsersValidator implements
		ConstraintValidator<PasswordRequiredForNewUsers, UserAndPassword> {

	public void initialize(PasswordRequiredForNewUsers constraintAnnotation) {

	}

	public boolean isValid(UserAndPassword userAndPassword,
			ConstraintValidatorContext context) {
		User user = userAndPassword.getUser();
		boolean valid = user.isIdSet() || userAndPassword.isPasswordSet();
		if (!valid) {
			// apply the error message to the "password" property
			// instead of the entire object
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Required")
					.addNode("password").addConstraintViolation();
		}
		return valid;
	}
}
