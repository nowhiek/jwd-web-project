package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.UserFieldValidator;
import by.epam.jwd.controller.validator.util.factory.UserValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.UserEmailValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserLoginValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.UserPasswordValidatorImpl;

public class UserValidatorFactoryImpl implements UserValidatorFactory {

	private static final UserValidatorFactoryImpl instance = new UserValidatorFactoryImpl();
	
	private static final UserFieldValidator<String> userLoginValidator = new UserLoginValidatorImpl();
	private static final UserFieldValidator<String> userPasswordValidator = new UserPasswordValidatorImpl();
	private static final UserFieldValidator<String> userEmailValidator = new UserEmailValidatorImpl();
	
	private UserValidatorFactoryImpl() {
	}
	
	public static UserValidatorFactoryImpl getInstance() {
		return instance;
	}
	
	@Override
	public UserFieldValidator<String> getUserLoginValidator() {
		return userLoginValidator;
	}

	@Override
	public UserFieldValidator<String> getUserPasswordValidator() {
		return userPasswordValidator;
	}

	@Override
	public UserFieldValidator<String> getUserEmailValidator() {
		return userEmailValidator;
	}
}
