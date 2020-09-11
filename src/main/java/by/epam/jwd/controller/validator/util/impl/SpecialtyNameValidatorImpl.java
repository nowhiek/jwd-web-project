package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.Regex;

public class SpecialtyNameValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String specialtyName) {
		return specialtyName != null && !specialtyName.isEmpty() && !specialtyName.toLowerCase().contains(Regex.SCRIPT);
	}
}
