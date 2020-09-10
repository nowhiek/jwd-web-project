package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserEmailValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String email) {
		return email.matches(Regex.USER_EMAIL_REGEX);
	}
}
