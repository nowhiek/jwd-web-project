package by.epam.jwd.controller.validator.util.factory.impl;

import java.sql.Date;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.UserDetailValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.UserDetailBirthdayValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserDetailNameValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserDetailPassportValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserDetailSexValidatorImpl;

public class UserDetailValidatorFacotyImpl implements UserDetailValidatorFactory {
    private static final UserDetailValidatorFacotyImpl instance = new UserDetailValidatorFacotyImpl();
	
	private static final FieldValidator<String> userDetailNameValidator = new UserDetailNameValidatorImpl();
	private static final FieldValidator<String> userDetailSexValidator = new UserDetailSexValidatorImpl();
	private static final FieldValidator<String> userDetailPassportValidator = new UserDetailPassportValidatorImpl();
	private static final FieldValidator<Date> userDetailBirthdayValidator = new UserDetailBirthdayValidatorImpl();
	
	private UserDetailValidatorFacotyImpl() {
	}
	
	public static UserDetailValidatorFacotyImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<String> getUserDetailNameValidator() {
		return userDetailNameValidator;
	}

	@Override
	public FieldValidator<String> getUserDetailPassportValidator() {
		return userDetailPassportValidator;
	}

	@Override
	public FieldValidator<Date> getUserDetailBirthdayValidator() {
		return userDetailBirthdayValidator;
	}

	@Override
	public FieldValidator<String> getUserSexValidator() {
		return userDetailSexValidator;
	}
}
