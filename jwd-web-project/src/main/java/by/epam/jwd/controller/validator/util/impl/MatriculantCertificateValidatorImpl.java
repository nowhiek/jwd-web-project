package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.UserFieldValidator;

public class MatriculantCertificateValidatorImpl implements UserFieldValidator<Integer> {

	@Override
	public boolean validate(Integer certificate) {
		return (certificate >= 0 && certificate <= 100);
	}
}
