package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.UserValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.UserEmailValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserLoginValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserPasswordValidatorImpl;

public class UserValidatorFactoryImpl implements UserValidatorFactory {

	private static final UserValidatorFactoryImpl instance = new UserValidatorFactoryImpl();
	
	private static final FieldValidator<String> userLoginValidator = new UserLoginValidatorImpl();
	private static final FieldValidator<String> userPasswordValidator = new UserPasswordValidatorImpl();
	private static final FieldValidator<String> userEmailValidator = new UserEmailValidatorImpl();
	
	private UserValidatorFactoryImpl() {
	}
	
	public static UserValidatorFactoryImpl getInstance() {
		return instance;
	}
	
	@Override
	public FieldValidator<String> getUserLoginValidator() {
		return userLoginValidator;
	}

	@Override
	public FieldValidator<String> getUserPasswordValidator() {
		return userPasswordValidator;
	}

	@Override
	public FieldValidator<String> getUserEmailValidator() {
		return userEmailValidator;
	}
}
