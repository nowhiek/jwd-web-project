package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.parameter.SessionParameter;
import by.epam.jwd.controller.validator.UserDetailValidator;
import by.epam.jwd.controller.validator.util.factory.UserDetailValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class UserDetailValidatorImpl implements UserDetailValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private UserDetailValidatorFactory userDetailValidatorFactory = validatorFactory.getUserDetailValidatorFactory();
	
	@Override
	public List<String> validate(UserDetail detail) {
		List<String> validation = new ArrayList<String>();
		
		if (detail.getFirstName() != null && !userDetailValidatorFactory.getUserDetailNameValidator().validate(detail.getFirstName())) {
			validation.add(SessionParameter.INVALID_NAME);
		}
		
		if (detail.getSecondName() != null && !userDetailValidatorFactory.getUserDetailNameValidator().validate(detail.getSecondName())) {
			validation.add(SessionParameter.INVALID_SURNAME);
		}
		
		if (detail.getSerialPassport() != null) {
			String passport = detail.getSerialPassport() + detail.getNumberPassport();
			
			if (!userDetailValidatorFactory.getUserDetailPassportValidator().validate(passport)) {
				validation.add(SessionParameter.INVALID_PASSPORT);
			}
		}
		
		if (detail.getBirthday() != null && !userDetailValidatorFactory.getUserDetailBirthdayValidator().validate(detail.getBirthday())) {
			validation.add(SessionParameter.INVALID_BIRTHDAY);
		}
		
		return validation;
	}
}
