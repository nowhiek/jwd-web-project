package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface FacultyValidatorFactory {
	
	FieldValidator<String> getFacultyNameValidator();
}
