package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;

public class UserDetailSexValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String sex) {
		return sex != null && !sex.isEmpty();
	}
}
