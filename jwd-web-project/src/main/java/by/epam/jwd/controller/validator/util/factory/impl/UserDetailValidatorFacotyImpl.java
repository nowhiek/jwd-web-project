package by.epam.jwd.controller.validator.util.factory.impl;

import java.sql.Date;

import by.epam.jwd.controller.validator.util.UserFieldValidator;
import by.epam.jwd.controller.validator.util.factory.UserDetailValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.UserDetailBirthdayValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserDetailNameValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserDetailPassportValidatorImpl;

public class UserDetailValidatorFacotyImpl implements UserDetailValidatorFactory {
    private static final UserDetailValidatorFacotyImpl instance = new UserDetailValidatorFacotyImpl();
	
	private static final UserFieldValidator<String> userDetailNameValidator = new UserDetailNameValidatorImpl();
	private static final UserFieldValidator<String> userDetailPassportValidator = new UserDetailPassportValidatorImpl();
	private static final UserFieldValidator<Date> userDetailBirthdayValidator = new UserDetailBirthdayValidatorImpl();
	
	private UserDetailValidatorFacotyImpl() {
	}
	
	public static UserDetailValidatorFacotyImpl getInstance() {
		return instance;
	}

	@Override
	public UserFieldValidator<String> getUserDetailNameValidator() {
		return userDetailNameValidator;
	}

	@Override
	public UserFieldValidator<String> getUserDetailPassportValidator() {
		return userDetailPassportValidator;
	}

	@Override
	public UserFieldValidator<Date> getUserDetailBirthdayValidator() {
		return userDetailBirthdayValidator;
	}
}
