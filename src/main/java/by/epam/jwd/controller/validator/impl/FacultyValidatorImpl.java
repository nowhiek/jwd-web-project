package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Faculty;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.FacultyValidator;
import by.epam.jwd.controller.validator.util.factory.FacultyValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class FacultyValidatorImpl implements FacultyValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private FacultyValidatorFactory facultyValidator = validatorFactory.getFacultyValidatorFactory();
	
	@Override
	public List<String> validate(Faculty faculty) {
		List<String> validation = new ArrayList<String>();
		
		if (!facultyValidator.getFacultyNameValidator().validate(faculty.getFacultyName())) {
			validation.add(ValidateParameter.INVALID_FACULTY_NAME);
		}
		
		return validation;
	}
}
