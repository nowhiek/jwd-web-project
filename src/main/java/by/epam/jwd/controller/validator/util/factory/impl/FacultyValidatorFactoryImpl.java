package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.FacultyValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.FacultyNameValidatorImpl;

public class FacultyValidatorFactoryImpl implements FacultyValidatorFactory {

	private static final FacultyValidatorFactoryImpl instance = new FacultyValidatorFactoryImpl();
	
	private static final FieldValidator<String> facultyNameValidator = new FacultyNameValidatorImpl();
	
	
	private FacultyValidatorFactoryImpl() {
	}
	
	public static FacultyValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<String> getFacultyNameValidator() {
		return facultyNameValidator;
	}
}
