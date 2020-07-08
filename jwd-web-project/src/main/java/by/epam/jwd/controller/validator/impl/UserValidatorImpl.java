package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.User;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.validator.UserValidator;
import by.epam.jwd.controller.validator.util.factory.UserValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class UserValidatorImpl implements UserValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private UserValidatorFactory userValidatorFactory = validatorFactory.getUserValidatorFactory();
	
	@Override
	public List<String> validate(User user) {
		List<String> validation = new ArrayList<String>();

		if (!userValidatorFactory.getUserLoginValidator().validate(user.getUserName())) {
			validation.add(SessionParameter.INVALID_LOGIN);
		}
		
		if (!userValidatorFactory.getUserPasswordValidator().validate(user.getUserPassword())) {
			validation.add(SessionParameter.INVALID_PASSWORD);
		}
		
		if (!userValidatorFactory.getUserEmailValidator().validate(user.getUserEmail())) {
			validation.add(SessionParameter.INVALID_EMAIL);
		}
		
		return validation;
	}
}
