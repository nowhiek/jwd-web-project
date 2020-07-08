package by.epam.jwd.controller.validator.util.factory;

import java.sql.Date;

import by.epam.jwd.controller.validator.util.UserFieldValidator;

public interface UserDetailValidatorFactory {

	UserFieldValidator<String> getUserDetailNameValidator();
	UserFieldValidator<String> getUserDetailPassportValidator();
	UserFieldValidator<Date> getUserDetailBirthdayValidator();
}
