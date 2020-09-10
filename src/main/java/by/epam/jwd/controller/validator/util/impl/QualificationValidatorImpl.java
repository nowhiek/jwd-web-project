package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.Regex;

public class QualificationValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String qualification) {
		return qualification != null && !qualification.isEmpty() && !qualification.toLowerCase().contains(Regex.SCRIPT);
	}
}
