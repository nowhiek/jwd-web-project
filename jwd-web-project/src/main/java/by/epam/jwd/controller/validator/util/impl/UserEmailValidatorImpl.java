package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.UserFieldValidator;

public class UserEmailValidatorImpl implements UserFieldValidator<String> {

	@Override
	public boolean validate(String email) {
		return email != null ? email.matches(Regex.USER_EMAIL_REGEX) : false;
	}
}
