package by.epam.jwd.controller.validator.util.factory.impl;

import by.epam.jwd.controller.validator.util.FieldValidator;
import by.epam.jwd.controller.validator.util.factory.ExamValidatorFactory;
import by.epam.jwd.controller.validator.util.impl.ExamMarkValidatorImpl;
import by.epam.jwd.controller.validator.util.impl.SubjectNameVlidatorImpl;

public class ExamValidatorFactoryImpl implements ExamValidatorFactory {

	private static final ExamValidatorFactoryImpl instance = new ExamValidatorFactoryImpl();
	
	private static final FieldValidator<Integer> examMarkValidator = new ExamMarkValidatorImpl();
	private static final FieldValidator<String> subjectNameValidator = new SubjectNameVlidatorImpl();
	
	
	private ExamValidatorFactoryImpl() {
	}
	
	public static ExamValidatorFactoryImpl getInstance() {
		return instance;
	}

	@Override
	public FieldValidator<Integer> getExamMarkValidator() {
		return examMarkValidator;
	}

	@Override
	public FieldValidator<String> getSubjectNameValidator() {
		return subjectNameValidator;
	}
}
