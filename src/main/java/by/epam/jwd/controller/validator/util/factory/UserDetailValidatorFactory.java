package by.epam.jwd.controller.validator.util.factory;

import java.sql.Date;

import by.epam.jwd.controller.validator.util.FieldValidator;

public interface UserDetailValidatorFactory {

	FieldValidator<String> getUserDetailNameValidator();
	FieldValidator<String> getUserSexValidator();
	FieldValidator<String> getUserDetailPassportValidator();
	FieldValidator<Date> getUserDetailBirthdayValidator();
}
