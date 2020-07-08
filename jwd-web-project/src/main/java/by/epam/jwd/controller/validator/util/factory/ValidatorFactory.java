package by.epam.jwd.controller.validator.util.factory;

import by.epam.jwd.controller.validator.util.factory.impl.MatriculantValidatorFactoryImpl;
import by.epam.jwd.controller.validator.util.factory.impl.UserDetailValidatorFacotyImpl;
import by.epam.jwd.controller.validator.util.factory.impl.UserValidatorFactoryImpl;

public class ValidatorFactory {

	private static final ValidatorFactory instance = new ValidatorFactory();
	
	private static final UserValidatorFactory userValidatorFactory = UserValidatorFactoryImpl.getInstance();
	private static final UserDetailValidatorFactory userDetailValidatorFactory = UserDetailValidatorFacotyImpl.getInstance();
	private static final MatriculantValidatorFactory matriculantValidatorFactory = MatriculantValidatorFactoryImpl.getInstance();
	
	private ValidatorFactory() {
	}
	
	public static ValidatorFactory getInstance() {
		return instance;
	}
	
	public UserValidatorFactory getUserValidatorFactory() {
		return userValidatorFactory;
	}
	
	public UserDetailValidatorFactory getUserDetailValidatorFactory() {
		return userDetailValidatorFactory;
	}
	
	public MatriculantValidatorFactory getMatriculantValidatorFactory() {
		return matriculantValidatorFactory;
	}
}
