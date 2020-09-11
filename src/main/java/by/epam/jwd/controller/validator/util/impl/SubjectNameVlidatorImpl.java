package by.epam.jwd.controller.validator.util.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.Regex;

public class SubjectNameVlidatorImpl implements FieldValidator<String> {

	@Override
	public boolean validate(String subjectName) {
		return subjectName != null && !subjectName.isEmpty() && !subjectName.toLowerCase().contains(Regex.SCRIPT);
	}
}
