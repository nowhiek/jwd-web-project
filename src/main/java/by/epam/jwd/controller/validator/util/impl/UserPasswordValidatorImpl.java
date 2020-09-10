package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserPasswordValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String password) {
		return password != null ? password.matches(Regex.USER_PASSWORD_REGEX) : false;
	}
}
