package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.UserDetail;
import by.epam.jwd.controller.parameter.ValidateParameter;
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
			validation.add(ValidateParameter.INVALID_NAME);
		}
		
		if (detail.getSecondName() != null && !userDetailValidatorFactory.getUserDetailNameValidator().validate(detail.getSecondName())) {
			validation.add(ValidateParameter.INVALID_SURNAME);
		}
		
		if (!userDetailValidatorFactory.getUserSexValidator().validate(detail.getSex())) {
			validation.add(ValidateParameter.INVALID_SEX);
		}
		
		if (detail.getSerialPassport() != null) {
			String passport = detail.getSerialPassport() + detail.getNumberPassport();
			
			if (!userDetailValidatorFactory.getUserDetailPassportValidator().validate(passport)) {
				validation.add(ValidateParameter.INVALID_PASSPORT);
			}
		}
		
		if (detail.getBirthday() != null && !userDetailValidatorFactory.getUserDetailBirthdayValidator().validate(detail.getBirthday())) {
			validation.add(ValidateParameter.INVALID_BIRTHDAY);
		}
		
		return validation;
	}
}
