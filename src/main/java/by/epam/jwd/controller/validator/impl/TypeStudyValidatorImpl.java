package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.TypeStudy;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.TypeStudyValidator;
import by.epam.jwd.controller.validator.util.factory.TypeStudyValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class TypeStudyValidatorImpl implements TypeStudyValidator {
	
	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private TypeStudyValidatorFactory typeStudyValidator = validatorFactory.getTypeStudyValidatorFactory();
	
	@Override
	public List<String> validate(TypeStudy type) {
		List<String> validation = new ArrayList<String>();
		
		if (!typeStudyValidator.getTypeStudyValidator().validate(type.getStudyName())) {
			validation.add(ValidateParameter.INVALID_TYPE_STUDY_NAME);
		}
		
		return validation;
	}
}