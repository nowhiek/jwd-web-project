package by.epam.jwd.controller.validator;

import by.epam.jwd.controller.validator.impl.MatriculantValidatorImpl;
import by.epam.jwd.controller.validator.impl.UserDetailValidatorImpl;
import by.epam.jwd.controller.validator.impl.UserValidatorImpl;

public class ValidatorFactoryImpl {

	private static final ValidatorFactoryImpl instance = new ValidatorFactoryImpl();
	
	private final UserValidator userValidator = new UserValidatorImpl();
	private final UserDetailValidator userDetailValidator = new UserDetailValidatorImpl();
	private final MatriculantValidator matriculantValidator = new MatriculantValidatorImpl();
	
	private ValidatorFactoryImpl(){}
	    
    public static ValidatorFactoryImpl getInstance(){
        return instance;
    }
    
    public UserValidator getUserValidator() {
    	return userValidator;
    }
    
    public UserDetailValidator getUserDetailValidator() {
    	return userDetailValidator;
    }
    
    public MatriculantValidator getMatriculantValidator() {
    	return matriculantValidator;
    }
}
