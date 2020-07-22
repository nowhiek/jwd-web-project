package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.UserFieldValidator;

public class MatriculantSpecialtyIdValidatorImpl implements UserFieldValidator<Integer> {

	@Override
	public boolean validate(Integer specialtyId) {
		return specialtyId > 0;
	}
}
