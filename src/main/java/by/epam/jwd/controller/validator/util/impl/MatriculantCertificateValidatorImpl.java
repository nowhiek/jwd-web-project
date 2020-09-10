package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;

public class MatriculantCertificateValidatorImpl implements FieldValidator<Integer> {

	@Override
	public boolean validate(Integer certificate) {
		return certificate >= 0 && certificate <= 100;
	}
}
