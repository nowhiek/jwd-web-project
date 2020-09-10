package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.Regex;

public class TypeStudyNameValidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String typeStudyName) {
		return typeStudyName != null && !typeStudyName.isEmpty() && !typeStudyName.toLowerCase().contains(Regex.SCRIPT);
	}
}

