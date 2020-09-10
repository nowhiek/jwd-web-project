package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserDetailPassportValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String passport) {
		return passport.matches(Regex.USER_PASSPORT_REGEX);
	}
}
