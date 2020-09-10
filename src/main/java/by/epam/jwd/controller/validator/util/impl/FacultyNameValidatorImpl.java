package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.Regex;

public class FacultyNameValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String facultyName) {
		return facultyName != null && !facultyName.isEmpty() && !facultyName.toLowerCase().contains(Regex.SCRIPT);
	}
}
