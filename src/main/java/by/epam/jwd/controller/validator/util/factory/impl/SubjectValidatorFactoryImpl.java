package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.SubjectValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.SubjectNameVlidatorImpl;

public class SubjectValidatorFactoryImpl implements SubjectValidatorFactory {

	private static final SubjectValidatorFactoryImpl instance = new SubjectValidatorFactoryImpl();
	
	private static final FieldValidator<String> subjectNameValidator = new SubjectNameVlidatorImpl();
	
	private SubjectValidatorFactoryImpl() {
	}
	
	public static SubjectValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<String> getSubjectNameValidator() {
		return subjectNameValidator;
	}
}

