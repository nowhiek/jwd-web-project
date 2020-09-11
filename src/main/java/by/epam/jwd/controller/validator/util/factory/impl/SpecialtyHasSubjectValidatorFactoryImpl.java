package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.SpecialtyHasSubjectValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.FacultyNameValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.SubjectNameVlidatorImpl;

public class SpecialtyHasSubjectValidatorFactoryImpl implements SpecialtyHasSubjectValidatorFactory {

	private static final SpecialtyHasSubjectValidatorFactoryImpl instance = new SpecialtyHasSubjectValidatorFactoryImpl();
	
	private static final FieldValidator<String> facultyNameValidator = new FacultyNameValidatorImpl();
	private static final FieldValidator<String> subjectNameValidator = new SubjectNameVlidatorImpl();
	
	private SpecialtyHasSubjectValidatorFactoryImpl() {
	}
	
	public static SpecialtyHasSubjectValidatorFactoryImpl getInstance() {
		return instance;
	}
	
	@Override
	public FieldValidator<String> getFacultyNameValidator() {
		return facultyNameValidator;
	}

	@Override
	public FieldValidator<String> getSubjectNameValidator() {
		return subjectNameValidator;
	}
}