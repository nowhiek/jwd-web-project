package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.Regex;
import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserDetailNameValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String name) {
		return name.matches(Regex.USER_FULL_NAME_REGEX);
	}
}
