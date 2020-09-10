package by.epam.jwd.controller.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.jwd.bean.Subject;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.controller.validator.SubjectValidator;
import by.epam.jwd.controller.validator.util.factory.SubjectValidatorFactory;
import by.epam.jwd.controller.validator.util.factory.ValidatorFactory;

public class SubjectValidatorImpl implements SubjectValidator {

	private ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
	private SubjectValidatorFactory subjectValidator = validatorFactory.getSubjectValidatorFactory();

	@Override
	public List<String> validate(Subject subject) {
		List<String> validation = new ArrayList<String>();
		
		if (!subjectValidator.getSubjectNameValidator().validate(subject.getSubjectName())) {
			validation.add(ValidateParameter.INVALID_SUBJECT_NAME);
		}
		
		return validation;
	}
}
