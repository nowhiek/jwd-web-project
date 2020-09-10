package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserLoginValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String login) {
		return login != null ? login.matches(Regex.USER_LOGIN_REGEX) : false;
	}
}
