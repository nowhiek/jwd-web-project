package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface UserValidatorFactory {
	
	FieldValidator<String> getUserLoginValidator();
	FieldValidator<String> getUserPasswordValidator();
	FieldValidator<String> getUserEmailValidator();
}
