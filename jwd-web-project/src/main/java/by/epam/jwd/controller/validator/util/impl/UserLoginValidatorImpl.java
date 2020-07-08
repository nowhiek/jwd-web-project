package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.UserFieldValidator;

public class UserLoginValidatorImpl implements UserFieldValidator<String> {

	@Override
	public boolean validate(String login) {
		return login != null ? login.matches(Regex.USER_LOGIN_REGEX) : false;
	}
}
