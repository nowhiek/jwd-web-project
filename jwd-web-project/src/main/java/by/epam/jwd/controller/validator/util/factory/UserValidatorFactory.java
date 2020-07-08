package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.UserFieldValidator;

public interface UserValidatorFactory {
	
	UserFieldValidator<String> getUserLoginValidator();
	UserFieldValidator<String> getUserPasswordValidator();
	UserFieldValidator<String> getUserEmailValidator();
}
